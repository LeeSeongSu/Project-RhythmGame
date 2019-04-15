/**
 * 
 */
package application;

/**
 * @author 양태일
 *	노트들을 시간에 맞게 떨어뜨릴 수 있게 만들어 주는 클래스
 */
public class Beat {
	
	private int time;
	private int noteLocation;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getNoteLocation() {
		return noteLocation;
	}
	public void setNoteLocation(int noteLocation) {
		this.noteLocation = noteLocation;
	}
	public Beat(int time, int noteLocation) {
		super();
		this.time = time;
		this.noteLocation = noteLocation;
	}
}
