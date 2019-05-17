package server;

public class RoomMember {
	private int threadNum;
	private int ready;
	private int score;
	
	public RoomMember(int threadNum, int ready, int score) {
		super();
		this.threadNum = threadNum;
		this.ready = ready;
		this.score = score;
	}
	
	public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	public int getReady() {
		return ready;
	}
	public void setReady(int ready) {
		this.ready = ready;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
