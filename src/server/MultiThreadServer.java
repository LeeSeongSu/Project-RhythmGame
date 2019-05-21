
package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
  * @FileName : MultiThreadServer.java
  * @Project : Project_DOS
  * @Date : 2019. 5. 13. 
  * @Author : Kim DongJin
  * @Comment :
 */
public class MultiThreadServer {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;

	private static int maxClientsCount = 10;
	private static ClientThread[] threads = new ClientThread[maxClientsCount];
	
	private static HashMap<String, ArrayList<Room>> roomSearchHash = new HashMap<String, ArrayList<Room>>();
	
	private static String[] musicList;

	public static void main(String args[]) {
		
		musicList = new String[5];
		musicList[0] = "HAPPY ROCK";
		musicList[1] = "BLANK";
		musicList[2] = "INVINCIBLE";
		musicList[3] = "NEKOZILLA";
		musicList[4] = "SKY HIGH";
		
		for(int i=0; i<musicList.length;i++) {
			roomSearchHash.put(musicList[i], new ArrayList<Room> ());
		}

		int portNumber = 9999;
		if (args.length < 1) {
			System.out
					.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
							+ "Now using port number=" + portNumber);
		} else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}

		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						(threads[i] = new ClientThread(clientSocket, threads))
								.start();
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(
							clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public static ArrayList<Room> searchRoomList(int musicIndex){ // clientThread에게 해당 곡의 방 리스트를 보내주기 위한 메소드
		return roomSearchHash.get(musicList[musicIndex]);
	}
}
