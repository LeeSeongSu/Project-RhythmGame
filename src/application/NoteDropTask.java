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
	
	public static int perfect;
	public static int great;
	public static int good;
	public static int bad;
	public static int miss;
	boolean oneCombo = true;
	int arrIndex = 0;
	static int item = 1;
	
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
			miss+=1;
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
			bad+=1;
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
			good+=1;
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
			great+=1;
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
			perfect+=1;
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
			great+=1;
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
			good+=1;
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
			bad+=1;
			Game.noteList.remove(note);
			note.close();
		}
		MultiThreadClient.sendScore(Game.getScore());
		
		if(Game.getScore()/5000==item) {
			ItemView.addItem((int) (Math.random()*3));
			item++;
		}
	}

	public void drawCombo() {
		
		String comboString = String.valueOf(Game.getCombo());
		char[] comboArr = comboString.toCharArray();
		for (int i = 0; i < comboArr.length; i++) {
			int startX = 960-comboArr.length*40;
			if(oneCombo||!pane.getChildren().contains(ImageStorage.comboImageView[0][(int) comboArr[i] - 48])) {
				ImageStorage.comboImageView[0][(int) comboArr[i] - 48].setLayoutX(startX + 80 * i);
				ImageStorage.comboImageView[0][(int) comboArr[i] - 48].setLayoutY(110);
				pane.getChildren().add(ImageStorage.comboImageView[0][(int) comboArr[i] - 48]);
				oneCombo=false;
			}else {
				for(int j=0;j<ImageStorage.comboImageView.length;j++) {
					if(!pane.getChildren().contains(ImageStorage.comboImageView[j][(int) comboArr[i] - 48])){
						ImageStorage.comboImageView[j][(int) comboArr[i] - 48].setLayoutX(startX + 80 * i);
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
			bad+=1;
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
			good+=1;
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
			great+=1;
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
			perfect+=1;
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
			great+=1;
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
			good+=1;
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
			bad+=1;
			Game.noteList.remove(note);
			note.close();
		}
		MultiThreadClient.sendScore(Game.getScore());
	}

	public static int getPerfect() {
		return perfect;
	}

	public static void setPerfect(int perfect) {
		NoteDropTask.perfect = perfect;
	}

	public static int getGreat() {
		return great;
	}

	public static void setGreat(int great) {
		NoteDropTask.great = great;
	}

	public static int getGood() {
		return good;
	}

	public static void setGood(int good) {
		NoteDropTask.good = good;
	}

	public static int getBad() {
		return bad;
	}

	public static void setBad(int bad) {
		NoteDropTask.bad = bad;
	}

	public static int getMiss() {
		return miss;
	}

	public static void setMiss(int miss) {
		NoteDropTask.miss = miss;
	}

}
