package application;

import java.awt.Graphics2D;
import java.awt.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author xoxod
 *
 */
public class Game extends Thread {
	
	AnchorPane pane;
	Music music;
	String title;
	Graphics2D graphic;
	Image image;
	
	
	public Game(String title, AnchorPane pane) {
		this.pane=pane;
		this.title=title;
		
		DOSApplicationController.introMusic.close();
		
	}
	
	public void playGame(){
		
		music=new Music(title, false);
		music.changeFlag();
		music.start();
		dropNotes();
		
	}
	
	public void dropNotes() {
		Note note=new Note(0,pane);
		note.screenDraw();
		note.start();
	}
	
	@Override
		public void run() {
		try {
			playGame();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
	

}
