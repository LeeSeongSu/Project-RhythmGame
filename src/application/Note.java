package application;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Note extends Thread{

	// 노트 이미지
	private Image noteBasicImage = (new ImageParser("note.png").getImage());
	private ImageView imageView;
	private AnchorPane pane;
	private int x, y = 0;//580-(1000/Main.SLEEP_TIME*Main.NOTE_SPEED)*Main.REACH_TIME; // 노트의 현재 위치
	
	int space=115;
	int startWidth=517;
	
	public Note(int x,AnchorPane pane) {
		this.x = x;
		this.pane=pane;
		imageView=new ImageView(noteBasicImage);
		imageView.setX(startWidth+space*x);
	}

	// 노트에 대한 이미지 그려주기
	public void screenDraw() {
		imageView.setY(y);
		pane.getChildren().add(imageView);
	}
	public void delete() {
		pane.getChildren().remove(imageView);
	}
	
	@Override
	public void run() {
		try {
			NoteDropTask<Void> task = new NoteDropTask<Void>(imageView, pane);
			 
			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
//		TranslateTransition tt = new TranslateTransition(new Duration(1000), imageView);
//		tt.setAutoReverse(false); tt.setCycleCount(1);
//		tt.setFromY(0); tt.setToY(950);tt.play();
	}

}
