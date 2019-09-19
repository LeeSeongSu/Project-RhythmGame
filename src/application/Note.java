package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Note extends Thread{
	
	private NoteDropTask<Void> task;
	
	// 노트 이미지
	private ImageView imageView;
	private AnchorPane pane;
	private int x, y = 800-(1000/DOSApplication.SLEEP_TIME*DOSApplication.NOTE_SPEED)*DOSApplication.REACH_TIME; // 노트의 현재 위치
	private boolean proceeded = true;
	int space=115;
	int startWidth=517;
	
	public Note(int x,AnchorPane pane) {
		this.x = x;
		this.pane=pane;
		imageView=new ImageView(ImageStorage.noteBasicImage);
		imageView.setX(startWidth+space*x);
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
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
			task = new NoteDropTask<Void>(this);
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

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setProceeded(boolean proceeded) {
		this.proceeded = proceeded;
	}

	public NoteDropTask<Void> getTask() {
		return task;
	}

	public void setTask(NoteDropTask<Void> task) {
		this.task = task;
	}
}
