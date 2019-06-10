package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
  * @FileName : clientThread.java
  * @Project : Project_DOS
  * @Date : 2019. 5. 13. 
  * @Author : Kim DongJin
  * @Comment : 서버가 실질적으로 작동하는 클라이언트 쓰레드
 */
public class ClientThread extends Thread {
	private String clientName = null;
	private DataInputStream is = null;
	private DataOutputStream os = null;
	private Socket clientSocket = null;
	private final  ClientThread[] threads;
	private int index;
	private int maxClientsCount;
	private int maxRoomMember =4;
	
	private ArrayList<Room> roomList;
	private Room room;
	private ArrayList<RoomMember> roomMembers;
	private String clientID;
	private int threadNum;
	private boolean stop = false;
	private boolean voiceMode;
	
	public ClientThread(Socket clientSocket, ClientThread[] threads , int index) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.index = index;
		maxClientsCount = threads.length;
	}

	public void run() { //클라이언트의 요청을 받는 곳
		int maxClientsCount = this.maxClientsCount;
		ClientThread[] threads = this.threads;
		
		RoomMember obj;
		int num=0;
		String[] words;
		
		try {
			is = new DataInputStream(clientSocket.getInputStream());
			os = new DataOutputStream(clientSocket.getOutputStream());
			
			for (int i = 0; i < maxClientsCount; i++) {
				if (threads[i] == this) {
					threads[i].os.writeUTF(i+"");
					threads[i].setName("thread "+i);
					threadNum=i;
					break;
				}
			}
			
			while(!stop) {
				String line = is.readUTF();
				
				if(line.startsWith("@joinRoom")) { // 방 정보
					words=line.split(" ");
					if(words[2].equals("true")) {
						voiceMode=true;
					}
					else
						voiceMode=false;
					
					roomMembers=searchRoom(Integer.parseInt(words[1]));
					
					for(int i=0; i<roomMembers.size(); i++) { // 방의 정보가 업데이트 될때 마다 해당방 클라이언트에게 신호를 보냄.
						obj=roomMembers.get(i);
						num=obj.getThreadNum();
						threads[num].os.writeUTF("@roomReset");
						for(int j=0; j<roomMembers.size(); j++) { 
							threads[num].os.writeUTF("@room "+threads[roomMembers.get(j).getThreadNum()].getClientID());
							threads[num].os.writeUTF("@ready "+roomMembers.get(j).getReady());
						}
					}
					
				}
				else if(line.startsWith("@id")) { //클라이언트 아이디 값
					words=line.split(" ");
					clientID=words[1];
				}
				else if(line.startsWith("@ready")) {
					
					for(int i=0; i<roomMembers.size();i++) {
						if(roomMembers.get(i).getThreadNum()==threadNum) {
							num=i;
							break;
						}
					}
					obj=roomMembers.get(num);
					
					if(obj.getReady()==1)
						obj.setReady(0);
					else
						obj.setReady(1);
					
					for(int i=0; i<roomMembers.size(); i++) { // 방의 정보가 업데이트 될때 마다 해당방 클라이언트에게 신호를 보냄.
						obj=roomMembers.get(i);
						num=obj.getThreadNum();
						threads[num].os.writeUTF("@readyReset");
						for(int j=0; j<roomMembers.size(); j++) { 
							threads[num].os.writeUTF("@ready "+roomMembers.get(j).getReady());
						}
					}
				}
				else if(line.startsWith("@start")) {
					for(int i=0; i<roomMembers.size();i++) {
						threads[roomMembers.get(i).getThreadNum()].os.writeUTF("@start");
					}
					room.setStart(true);
					
				}
				
				else if(line.startsWith("@score")) {
					words=line.split(" ");
					
					for(int i=0; i<roomMembers.size();i++) {
						if(roomMembers.get(i).getThreadNum()==threadNum) {
							roomMembers.get(i).setScore(Integer.parseInt(words[1]));
							break;
						}
					}
					
					Collections.sort(roomMembers, new Comparator<RoomMember>() {

						@Override
						public int compare(RoomMember o1, RoomMember o2) {
							// TODO Auto-generated method stub
							return o2.getScore()-o1.getScore();
						}
					});
					
					
					for(int i=0; i<roomMembers.size(); i++) { 
						obj=roomMembers.get(i);
						num=obj.getThreadNum();
						threads[num].os.writeUTF("@scoreReset");
						for(int j=0; j<roomMembers.size(); j++) { 
							threads[num].os.writeUTF("@score "+threads[roomMembers.get(j).getThreadNum()].getClientID()+" "+roomMembers.get(j).getScore());
						}
						threads[num].os.writeUTF("@scoreEnd");
					}
					
				}
				else if(line.startsWith("@roomExit")){
					if(roomMembers!=null) {
						for(int i=0; i<roomMembers.size(); i++) {
							if(roomMembers.get(i).getThreadNum()==threadNum) {
								roomMembers.remove(i);
							}
						}
							
						
						for(int i=0; i<roomMembers.size(); i++) { 
							obj=roomMembers.get(i);
							num=obj.getThreadNum();
							threads[num].os.writeUTF("@roomReset");
							for(int j=0; j<roomMembers.size(); j++) { 
								threads[num].os.writeUTF("@room "+threads[roomMembers.get(j).getThreadNum()].getClientID());
								threads[num].os.writeUTF("@ready "+roomMembers.get(j).getReady());
							}
						}
					}
					System.out.println("Exit");
				}
				else if(line.startsWith("@exit")) {
					stop=true;
					threads[index]=null;
				}
				else {
					for (int i = 0; i < maxClientsCount; i++) {
						if(threads[i]!=null) {
							threads[i].os.writeUTF(line);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<RoomMember> searchRoom(int musicIndex){  //서버에서 받아온 방 리스트에서 적절한 방을 고르는 메소드
		roomList=MultiThreadServer.searchRoomList(musicIndex);
		ArrayList<RoomMember> newRoomList;
		for(int i=0; i<roomList.size();i++) {
			newRoomList=roomList.get(i).getRoomMembers();
			
			if(roomList.get(i).isVoiceMode()==voiceMode&&!roomList.get(i).isStart()&&newRoomList.size()<=maxRoomMember) {
				newRoomList.add(new RoomMember (threadNum,0,0));
				room=roomList.get(i);
				return newRoomList;
			}
				
		}
		
		Room newRoom = new Room(voiceMode);
		roomList.add(newRoom);
		room=newRoom;
		newRoom.getRoomMembers().add(new RoomMember (threadNum,0,0));
		return newRoom.getRoomMembers();
	}
	
	
	
	public String getClientID() {
		return clientID;
		
	}
}
