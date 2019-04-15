package application;

import java.awt.Image;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;

/**
 * @author 양태일
 *	게임화면에서의 노트를 음악에 맞게 떨어트리는 클래스
 */
public class Game extends Thread {

	AnchorPane pane;
	Music music;
	String title;
	Image image;
	LinkedList<Note> noteList = new LinkedList<>();
	public Game(String title, AnchorPane pane) {
		this.pane = pane;
		this.title = title;
		DOSApplicationController.introMusic.close();

	}

	public void playGame() {
		dropNotes();
//		try {
//			Task<Void> task = new Task<Void>() {
//			    public Void call() throws Exception {
//					Platform.runLater(()->dropNotes());
//			        return null;
//			    }
//			};
//			 
//			Thread thread = new Thread(task);
//			thread.setDaemon(true);
//			thread.start();
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
	public void startMusic() {
		music = new Music(title, false);
		music.changeFlag();
		music.start();
	}

	public void dropNotes(){
		boolean flag =false;
		int startTime = 1000-Main.REACH_TIME*1000;
		music = new Music(title, false);
		Thread t = new Thread(new MakeGameTask(music,pane));
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		try {
			Task<Void> task = new Task<Void>() {
			    public Void call() throws Exception {
					Platform.runLater(()->playGame());
			        return null;
			    }
			};
			 
			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
