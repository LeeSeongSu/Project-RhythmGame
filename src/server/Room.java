package server;

import java.util.ArrayList;

public class Room {
	private int roomNum;
	private ArrayList<RoomMember> roomMembers;
	private boolean start;
	
	public Room() {
		roomMembers=new ArrayList<RoomMember> ();
		start=false;
	}
	public int getRoomNum() {
		return roomNum;
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
