package application;

import java.awt.Image;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * @author 양태일
 *	게임화면에서의 노트를 음악에 맞게 떨어트리는 클래스
 */

public class Game extends Thread {
	
	Scene sc;
	AnchorPane pane;
	Music music;
	String title;
	Image image;
	VoiceKeyListener voice;
	Boolean isVoiceMode, playing;
	ArrayList<String> room;
	ArrayList<String> scores;
	
	public static int combo=0;
	public static int score=0;
	
	static ArrayList<Note> noteList = new ArrayList<>();
	
	private ArrayList<Label> textList = new ArrayList<Label>();
	
	public static synchronized void addScore(int addScore) {
		score = score+addScore+combo*10;
	}
	
	public static synchronized void addCombo() {
		combo++;
	}
	
	public static synchronized void resetCombo() {
		combo=0;
	}
	
	
	public Game(String title, AnchorPane pane, Scene sc) {
		this.pane = pane;
		this.title = title;
		this.sc = sc;
		DOSApplicationController.introMusic.close();
		
		room=MultiThreadClient.getRoom();
		scores=MultiThreadClient.getScore();
		
		Label label;
		
		playing=true;
		
		for(int i=0; i<room.size(); i++) {
			scores.add("0");
			label = new Label(i+1+". "+room.get(i)+" "+scores.get(i));
			label.setLayoutX(150);
			label.setLayoutY(300+35*i);
			label.setStyle("-fx-text-fill: white;");
			pane.getChildren().add(label);
			
			textList.add(label);
		}
		
		Thread thread = new Thread() { // fx UI를 변경하는 쓰레드에 접근하기 위해 만든 쓰레드
            @Override
            public void run() {
            	
                while (playing) {
                    Platform.runLater(() -> {

                    	room= MultiThreadClient.getRoom();
                    	scores=MultiThreadClient.getScore();
                		for(int i=0; i<room.size();i++) {
                			textList.get(i).setText(i+1+". "+room.get(i)+" "+scores.get(i));;
                	
                		}
                    });
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
            }
        };
        thread.setName("gameScoreUpdate");
        thread.setDaemon(true);
        thread.start();
		
		
		sc.setOnKeyPressed(new KeyListener(pane, noteList));
		sc.setOnKeyReleased(new NoteEffectKeyListener(pane));
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

	public String getTitle() {
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

}
