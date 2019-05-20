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
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.missImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.missImage);
			}
			Game.resetCombo();
			Game.noteList.remove(note);
			note.close();
		}
	}
	
	public void judge() {
		if( y >= 1005) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		}else if( y >= 945) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Game.addCombo();
			Game.addScore(80);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=885) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Game.addCombo();
			Game.addScore(90);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=825) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.perfectImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.perfectImage);
			}
			Game.addCombo();
			Game.addScore(100);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=765) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Game.addCombo();
			Game.addScore(90);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=705) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Game.addCombo();
			Game.addScore(80);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=645) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		}
		System.out.println("combo = " +Game.combo);
		MultiThreadClient.sendScore(Game.score);
	}
	
	public void soundJudge() {
		if( y >= 1035) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		}else if( y >= 965) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Game.addCombo();
			Game.addScore(80);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=895) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Game.addCombo();
			Game.addScore(90);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=815) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.perfectImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.perfectImage);
			}
			Game.addCombo();
			Game.addScore(100);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=755) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Game.addCombo();
			Game.addScore(90);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=675) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Game.addCombo();
			Game.addScore(80);
			Game.noteList.remove(note);
			note.close();
		}else if( y>=615) {
			if(ImageStorage.judgeImgView.getImage()==null||!ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		}
		System.out.println("combo = " +Game.combo);
		MultiThreadClient.sendScore(Game.score);

	}

}
