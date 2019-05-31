package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NoteDropTask<Void> extends Task<Void> {

	ImageView imgView;
	AnchorPane pane;
	int y;
	Note note;
	List<ImageView> addedImgView = new ArrayList<ImageView>();
	ImageView[] addedImgViewArr = new ImageView[4];
	boolean oneCombo = true;
	int arrIndex = 0;
	public NoteDropTask(Note note) {
		this.imgView = note.getImageView();
		this.pane = note.getPane();
		this.y = note.getY();
		this.note = note;
		
		for( int i = 0; i<4; i++) {
			addedImgViewArr[i]=null;
		}
	}

	@Override
	protected Void call() throws Exception {
		while (true) {
			if (note.isProceeded()) {
				drop();
				Platform.runLater(() -> imgView.setLayoutY(y));
				Thread.sleep(Main.SLEEP_TIME);
			} else {
				Platform.runLater(() -> pane.getChildren().remove(imgView));
				this.cancel();
				break;
			}
		}
		return null;
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 1100) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.missImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.missImage);
			}
			oneCombo=true;
			Platform.runLater(()->eraseCombo());
			Game.resetCombo();
			Game.noteList.remove(note);
			note.close();
		}
	}

	public void judge() {
		if (y >= 1005) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Platform.runLater(()->eraseCombo());
			oneCombo=true;
			Game.resetCombo();
			Game.addScore(70);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 945) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(80);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 885) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(90);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 825) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.perfectImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.perfectImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(100);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 765) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(90);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 705) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(80);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 645) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Platform.runLater(()->eraseCombo());
			oneCombo=true;
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		}
		System.out.println("combo = " + Game.getCombo());
		MultiThreadClient.sendScore(Game.getScore());
	}

	public void drawCombo() {
		
		String comboString = String.valueOf(Game.getCombo());
		char[] comboArr = comboString.toCharArray();
		for (int i = 0; i < comboArr.length; i++) {
			if(oneCombo||!pane.getChildren().contains(ImageStorage.comboImageView[0][(int) comboArr[i] - 48])) {
				ImageStorage.comboImageView[0][(int) comboArr[i] - 48].setLayoutX(920 - 80 * (comboArr.length - i - 1));
				ImageStorage.comboImageView[0][(int) comboArr[i] - 48].setLayoutY(110);
				pane.getChildren().add(ImageStorage.comboImageView[0][(int) comboArr[i] - 48]);
				oneCombo=false;
			}else {
				for(int j=0;j<ImageStorage.comboImageView.length;j++) {
					if(!pane.getChildren().contains(ImageStorage.comboImageView[j][(int) comboArr[i] - 48])){
						ImageStorage.comboImageView[j][(int) comboArr[i] - 48].setLayoutX(920 - 80 * (comboArr.length - i - 1));
						ImageStorage.comboImageView[j][(int) comboArr[i] - 48].setLayoutY(110);
						pane.getChildren().add(ImageStorage.comboImageView[j][(int) comboArr[i] - 48]);
						break;
					}
				}
			}
		}
		if(!pane.getChildren().contains(ImageStorage.comboWordImageView))
			pane.getChildren().add(ImageStorage.comboWordImageView);
	}

	public void eraseCombo() {
		for (int i = 0; i < ImageStorage.comboImageView.length; i++) {
			for(int j=0; j< ImageStorage.comboImageView[i].length;j++) {
				ImageView tmp = ImageStorage.comboImageView[i][j];
				if (pane.getChildren().contains(tmp)) {
					pane.getChildren().remove(tmp);
				}
			}
		}
		if(pane.getChildren().contains(ImageStorage.comboWordImageView))
			pane.getChildren().remove(ImageStorage.comboWordImageView);
	}

	public void soundJudge() {
		if (y >= 1035) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Platform.runLater(()->eraseCombo());
			oneCombo=true;
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 965) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(80);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 895) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(90);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 815) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.perfectImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.perfectImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(100);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 755) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.greatImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.greatImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(90);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 675) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.goodImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.goodImage);
			}
			Platform.runLater(()->eraseCombo());
			Game.addCombo();
			Game.addScore(80);
			Platform.runLater(() -> drawCombo());
			Game.noteList.remove(note);
			note.close();
		} else if (y >= 615) {
			if (ImageStorage.judgeImgView.getImage() == null
					|| !ImageStorage.judgeImgView.getImage().equals(ImageStorage.badImage)) {
				ImageStorage.judgeImgView.setImage(ImageStorage.badImage);
			}
			Platform.runLater(()->eraseCombo());
			oneCombo=true;
			Game.resetCombo();
			Game.addScore(70);
			Game.noteList.remove(note);
			note.close();
		}
		System.out.println("combo = " + Game.getCombo());
		MultiThreadClient.sendScore(Game.getScore());
	}

}
