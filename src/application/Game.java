package application;

import java.util.ArrayList;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * @author 양태일
 *	게임화면에서의 노트를 음악에 맞게 떨어트리는 클래스
 */

public class Game extends Thread {
	
	private Scene sc;
	private AnchorPane pane;
	private Music music;
	private static String title;
	private Image image;
	private VoiceKeyListener voice;
	private boolean isVoiceMode;
	
	private static int combo=0;
	private static int score=0;

	static ArrayList<Note> noteList = new ArrayList<>();
	
	
	public static synchronized void addScore(int addScore) {
		score = score+addScore+combo*10;
	}
	
	public static synchronized void addCombo() {
		combo++;
	}
	
	public static synchronized void resetCombo() {
		combo=0;
	}
	
	public Game(String title, AnchorPane pane, Scene sc, boolean isVoiceMode) {
		this.pane = pane;
		this.title = title;
		this.sc = sc;
		this.isVoiceMode= isVoiceMode;
		DOSApplicationController.introMusic.close();
		sc.setOnKeyPressed(new KeyListener(pane, noteList));
		sc.setOnKeyReleased(new NoteEffectKeyListener(pane));
		if(isVoiceMode) {
			voiceMode();
		}
		
	}
	public void voiceMode() {
		voice=new VoiceKeyListener(pane);
	}
	
	public void playGame() {
		dropNotes();
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
		Thread t = new Thread(new MakeGameTask(music,pane,noteList));
		t.setDaemon(true);
		t.start();
		Thread voiceT = new Thread(voice);
		voiceT.start();
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

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public static String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ArrayList<Note> getNoteList() {
		return noteList;
	}

	public void setNoteList(ArrayList<Note> noteList) {
		this.noteList = noteList;
	}
	
	public static int getCombo() {
		return combo;
	}

	public static void setCombo(int combo) {
		Game.combo = combo;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Game.score = score;
	}

}
