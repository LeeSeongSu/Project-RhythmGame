package application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 
  * @FileName : MultiThreadClient.java
  * @Project : Project_DOS
  * @Date : 2019. 5. 13. 
  * @Author : Kim DongJin
  * @Comment :
 */
public class MultiThreadClient implements Runnable {
	
	private static Socket clientSocket = null;
	private static PrintStream os = null;
	private static DataInputStream is = null;

	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	
	public static String threadNum; 
	public static String clientId="";
	private static ArrayList<String> room;
	private static ArrayList<String> ready;
	private static ArrayList<String> score;
	
	public static boolean flag = false;

	public static void main(String[] args) {

		int portNumber = 9999;
//		String host = "192.168.0.45";
		String host = "localhost";

		if (args.length < 2) {
			System.out.println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
		+ "Now using host="+ host+ ", portNumber="+ portNumber);
		} 
		else {
			host = args[0];
			portNumber = Integer.valueOf(args[1]).intValue();
		}
		
		try {
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
							+ host);
		}
		new Thread(new MultiThreadClient()).start();
		DOSApplication.launch(DOSApplication.class);
	}

	public void run() {// 클라이언트가 서버에게 데이터를 받는 부분
		
		try {
			
			String responseLine;
			String[] words;
			threadNum=is.readLine();
			System.out.println(threadNum);
			
			room=new ArrayList<String>();
			ready=new ArrayList<String>();
			score=new ArrayList<String>();
			
			while ((responseLine = is.readLine()) != null) {
				if (responseLine.indexOf("quit") != -1)
					break;
				else if(responseLine.startsWith("@roomReset")) {
					room.clear();
					ready.clear();
				}
				else if(responseLine.startsWith("@room")) {
					words=responseLine.split(" ");
					room.add(words[1]);
				}
				else if(responseLine.startsWith("@readyReset"))
					ready.clear();
				else if(responseLine.startsWith("@ready")) {
					words=responseLine.split(" ");
					ready.add(words[1]);
				}
				else if(responseLine.startsWith("@start")) {
					MultiScreenView.setStart(true);
				}
				else if(responseLine.startsWith("@scoreReset")) {
					score.clear();
					room.clear();
				}
				else if(responseLine.startsWith("@score")) {
					words=responseLine.split(" ");
					room.add(words[1]);
					score.add(words[2]);
				}
			}
			os.close();
			is.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		}
		
		
	}
	
	public static void sendID(String value) { //서버에게 클라이언트가 로그인한 아이디 값을 보냄
		os.println("@id "+ value);
		clientId=value;
	}
	
	public static void joinRoom(int musicIndex) { // 사용자가 원하는 방에 입장하기 위해 서버에게 방 리스트를 보내달라고 요청하는 메소드
		os.println("@joinRoom "+musicIndex);
	}
	
	public static void ready() {
		os.println("@ready");
	}
	
	public static void start() {
		os.println("@start");
	}
	
	public static void sendScore(int value) {
		os.println("@score "+value);
	}
	
	public static void roomExit() {
		os.println("@roomExit");
	}
	public static void exit() {
		os.println("@exit");
	}

	public static ArrayList<String> getRoom(){
		return room;
	}
	public static ArrayList<String> getReady(){
		return ready;
	}
	public static ArrayList<String> getScore(){
		return score;
	}
}
