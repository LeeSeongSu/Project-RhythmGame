package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NoteDropTask<Void> extends Task<Void> {

	ImageView imgView;
	AnchorPane pane;
	int y;
	Note note;
	
	public NoteDropTask(Note note) {
		this.imgView = note.getImageView();
		this.pane = note.getPane();
		this.y = note.getY();
		this.note = note;
	}

	@Override
	protected Void call() throws Exception {
		while (true) {
			if(note.isProceeded()) {
				drop();
				Platform.runLater(()-> imgView.setLayoutY(y));
				Thread.sleep(Main.SLEEP_TIME);
			}else {
				Platform.runLater(()->pane.getChildren().remove(imgView));
				this.cancel();
				break;
			}
		}
		return null;
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y>1100) {
			System.out.println("Miss");
			note.close();
		}
	}
	
	public void judge() {
		System.out.println(y);
		if( y >= 1005) {
			System.out.println("Late");
			note.close();
		}else if( y >= 945) {
			System.out.println("Good");
			note.close();
		}else if( y>=885) {
			System.out.println("Great");
			note.close();
		}else if( y>=825) {
			System.out.println("Perfect");
			note.close();
		}else if( y>=765) {
			System.out.println("Great");
			note.close();
		}else if( y>=705) {
			System.out.println("Good");
			note.close();
		}else if( y>=645) {
			System.out.println("Early");
			note.close();
		}
	}
	
	public void soundJudge() {
		if( y >= 1035) {
			System.out.println("Late");
			note.close();
		}else if( y >= 965) {
			System.out.println("Good");
			note.close();
		}else if( y>=895) {
			System.out.println("Great");
			note.close();
		}else if( y>=815) {
			System.out.println("Perfect");
			note.close();
		}else if( y>=755) {
			System.out.println("Great");
			note.close();
		}else if( y>=675) {
			System.out.println("Good");
			note.close();
		}else if( y>=615) {
			System.out.println("Early");
			note.close();
		}
	}

}
