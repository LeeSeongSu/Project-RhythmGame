package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author xoxod
 *
 */
public class KeyListener implements EventHandler<KeyEvent> {
	ArrayList<Note> noteList;
	ImageView effectImgView;
	ImageStorage storage = new ImageStorage();
	AnchorPane pane;

	public KeyListener(AnchorPane pane, ArrayList<Note> noteList) {
		this.noteList = noteList;
		this.pane = pane;
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
		case S:
			System.out.println("S");
			drawEffect(0);
			judge(0);
			break;
		case D:
			System.out.println("D");
			drawEffect(1);
			judge(1);
			break;
		case F:
			System.out.println("F");
			drawEffect(2);
			judge(2);
			break;
		case J:
			System.out.println("J");
			drawEffect(4);
			judge(4);
			break;
		case K:
			System.out.println("K");
			drawEffect(5);
			judge(5);
			break;
		case L:
			System.out.println("L");
			drawEffect(6);
			judge(6);
			break;
			
		case Z:item.items(0);break;
		case X:item.items(1);break;
		case C:item.items(2);break;
		case ESCAPE:
			System.out.println("ESC");
			Game.pause();
			break;
		
		}
		
		if (!Game.isVoiceMode()) {
			switch (event.getCode()) {
			case SPACE:
				System.out.println("SPACE");
				drawEffect(3);
				judge(3);
				break;
			}
		}
	}

	public void drawEffect(int index) {
		effectImgView = storage.effectImgView[index];
		if (!pane.getChildren().contains(effectImgView))
			pane.getChildren().add(effectImgView);
	}

	public void judge(int i) {
		Iterator<Note> iter = noteList.iterator();
		while (iter.hasNext()) {
			Note note = iter.next();
			if (note.getX() == i) {
				try {
					note.getTask().judge();
					break;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
