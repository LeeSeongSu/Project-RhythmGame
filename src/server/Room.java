package server;

import java.util.ArrayList;

public class Room {
	private int roomNum;
	private boolean voiceMode;
	private ArrayList<RoomMember> roomMembers;
	private boolean start;
	
	public Room(boolean voiceMode) {
		roomMembers=new ArrayList<RoomMember> ();
		start=false;
		this.voiceMode=voiceMode;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public boolean isVoiceMode() {
		return voiceMode;
	}
	public void setVoiceMode(boolean voiceMode) {
		this.voiceMode = voiceMode;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public ArrayList<RoomMember> getRoomMembers() {
		return roomMembers;
	}
	public void setRoomMembers(ArrayList<RoomMember> roomMembers) {
		this.roomMembers = roomMembers;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	
	

}
