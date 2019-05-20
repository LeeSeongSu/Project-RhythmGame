package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

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
	private PrintStream os = null;
	private Socket clientSocket = null;
	private final  ClientThread[] threads;
	private int maxClientsCount;
	
	private ArrayList<ArrayList<RoomMember>> roomList;
	private ArrayList<RoomMember> room;
	private String clientID;
	private int threadNum;
	private boolean stop = false;
	
	public ClientThread(Socket clientSocket, ClientThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
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
			os = new PrintStream(clientSocket.getOutputStream());
			
			for (int i = 0; i < maxClientsCount; i++) {
				if (threads[i] == this) {
					threads[i].os.println(i);
					threads[i].setName("thread "+i);
					threadNum=i;
					break;
				}
			}
			
			while(!stop) {
				String line = is.readLine();
				
				if(line.startsWith("@joinRoom")) { // 방 정보
					words=line.split(" ");
					room=searchRoom(Integer.parseInt(words[1]));
					for(int i=0; i<room.size(); i++) { // 방의 정보가 업데이트 될때 마다 해당방 클라이언트에게 신호를 보냄.
						obj=room.get(i);
						num=obj.getThreadNum();
						threads[num].os.println("@roomReset");
						for(int j=0; j<room.size(); j++) { 
							threads[num].os.println("@room "+threads[room.get(j).getThreadNum()].getClientID());
							threads[num].os.println("@ready "+room.get(j).getReady());
						}
					}
					
				}
				else if(line.startsWith("@id")) { //클라이언트 아이디 값
					words=line.split(" ");
					clientID=words[1];
				}
				else if(line.startsWith("@ready")) {
					
					for(int i=0; i<room.size();i++) {
						if(room.get(i).getThreadNum()==threadNum) {
							num=i;
							break;
						}
					}
					obj=room.get(num);
					
					if(obj.getReady()==1)
						obj.setReady(0);
					else
						obj.setReady(1);
					
					for(int i=0; i<room.size(); i++) { // 방의 정보가 업데이트 될때 마다 해당방 클라이언트에게 신호를 보냄.
						obj=room.get(i);
						num=obj.getThreadNum();
						threads[num].os.println("@readyReset");
						for(int j=0; j<room.size(); j++) { 
							threads[num].os.println("@ready "+room.get(j).getReady());
						}
					}
				}
				else if(line.startsWith("@start")) {
					for(int i=0; i<room.size();i++) {
						threads[room.get(i).getThreadNum()].os.println("@start");
					}
				}
				
				else if(line.startsWith("@score")) {
					words=line.split(" ");
					
					for(int i=0; i<room.size();i++) {
						if(room.get(i).getThreadNum()==threadNum) {
							room.get(i).setScore(Integer.parseInt(words[1]));
							break;
						}
					}
					
					Collections.sort(room, new Comparator<RoomMember>() {

						@Override
						public int compare(RoomMember o1, RoomMember o2) {
							// TODO Auto-generated method stub
							return o2.getScore()-o1.getScore();
						}
					});
					
					
					for(int i=0; i<room.size(); i++) { 
						obj=room.get(i);
						num=obj.getThreadNum();
						threads[num].os.println("@scoreReset");
						for(int j=0; j<room.size(); j++) { 
							threads[num].os.println("@score "+threads[room.get(j).getThreadNum()].getClientID()+" "+room.get(j).getScore());
						}
					}
					
				}
				else if(line.startsWith("@roomExit")){
					if(room!=null) {
						for(int i=0; i<room.size(); i++) {
							if(room.get(i).getThreadNum()==threadNum) {
								room.remove(i);
							}
						}
						
						for(int i=0; i<room.size(); i++) { 
							obj=room.get(i);
							num=obj.getThreadNum();
							threads[num].os.println("@roomReset");
							for(int j=0; j<room.size(); j++) { 
								threads[num].os.println("@room "+threads[room.get(j).getThreadNum()].getClientID());
								threads[num].os.println("@ready "+room.get(j).getReady());
							}
						}
					}
					System.out.println("Exit");
				}
				else if(line.startsWith("@exit")) {
					stop=true;
				}
				else {
					for (int i = 0; i < maxClientsCount; i++) {
						if(threads[i]!=null) {
							threads[i].os.println(line);
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

		for(int i=0; i<roomList.size();i++) {
			if(roomList.get(i).size()!=4) {
				roomList.get(i).add(new RoomMember (threadNum,0,0));
				return roomList.get(i);
			}
				
		}
		
		ArrayList<RoomMember> newRoomList = new ArrayList<RoomMember>();
		roomList.add(newRoomList);
		newRoomList.add(new RoomMember (threadNum,0,0));
		return newRoomList;
	}
	
	
	
	public String getClientID() {
		return clientID;
		
	}
}
