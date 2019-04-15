package application;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * @author xoxod
 *
 */
public class KeyListener implements EventHandler<KeyEvent>{
	ArrayList<Note> noteList;
	
	public KeyListener(ArrayList<Note> noteList) {
		this.noteList = noteList;
	}

	@Override
	public void handle(KeyEvent event) {
		switch(event.getCode()) {
		case S:	System.out.println("S"); judge(0); break;
		case D:	System.out.println("D"); judge(1); break;
		case F:	System.out.println("F"); judge(2); break;
		case J:	System.out.println("J"); judge(4); break;
		case K:	System.out.println("K"); judge(5); break;
		case L:	System.out.println("L"); judge(6); break;
		}
	}
	
	public void judge(int i) {
		for(Note note : noteList) {
			if(note.getX()==i) {
				try {
					note.getTask().judge();
				}catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}
	
}
