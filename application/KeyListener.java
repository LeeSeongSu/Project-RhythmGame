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
		case ESCAPE:
			Game.pause();
			break;
			
		case S:
			drawEffect(0);
			judge(0);
			break;
		case D:
			drawEffect(1);
			judge(1);
			break;
		case F:
			drawEffect(2);
			judge(2);
			break;
		case J:
			drawEffect(4);
			judge(4);
			break;
		case K:
			drawEffect(5);
			judge(5);
			break;
		case L:
			drawEffect(6);
			judge(6);
			break;
			
		case Z:Item.items(0);break;
		case X:Item.items(1);break;
		case C:Item.items(2);break;
		
		}
		
		if (!Game.isVoiceMode()) {
			switch (event.getCode()) {
			case SPACE:
				drawEffect(3);
				judge(3);
				break;
			}
		}
		
		if(Game.isMultiMode()) {
			switch (event.getCode()) {
				case DIGIT1:
					ItemView.useItem(MultiThreadClient.getRoom().get(0));
					break;
				case DIGIT2:
					if(MultiThreadClient.getRoom().size()>1)
						ItemView.useItem(MultiThreadClient.getRoom().get(1));
					break;
				case DIGIT3:
					if(MultiThreadClient.getRoom().size()>2)
						ItemView.useItem(MultiThreadClient.getRoom().get(2));
					break;
				case DIGIT4:
					if(MultiThreadClient.getRoom().size()>3)
						ItemView.useItem(MultiThreadClient.getRoom().get(3));
					break;
				case DIGIT5:
					if(MultiThreadClient.getRoom().size()>4)
						ItemView.useItem(MultiThreadClient.getRoom().get(4));
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
