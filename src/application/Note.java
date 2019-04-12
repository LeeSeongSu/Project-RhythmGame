package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Note extends Thread {

	// 노트 이미지
	private Image noteBasicImage = (new ImageParser("note.png").getImage());
	private ImageView imageView;
	private AnchorPane pane;
	private int x, y; // 노트의 현재 위치
	
	int space=115;
	int startWidth=517;
	
	public Note(int x,AnchorPane pane) {
		this.x = x;
		this.pane=pane;
		y=0;
		imageView=new ImageView(noteBasicImage);
		imageView.setX(startWidth+space*x);
	}

	// 노트에 대한 이미지 그려주기
	public void screenDraw() {
		imageView.setY(y);
		pane.getChildren().add(imageView);
	}
	
	public void drop() {
		y+=7;
		imageView.setY(y);
	}
	
	public void delete() {
		pane.getChildren().remove(imageView);
	}

	@Override
	public void run() {
		try {
			while(true) {
				if(y>950) {
					delete();
					break;
				}
				drop();
				Thread.sleep(10);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
