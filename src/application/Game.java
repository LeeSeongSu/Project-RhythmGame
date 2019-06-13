package application;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * @author 양태일
 *	게임화면에서의 노트를 음악에 맞게 떨어트리는 클래스
 */

public class Game extends Thread {
	
	private Scene sc;
	private static AnchorPane pane;
	private static Music music;
	private static String title;
	private Image image;
	private VoiceKeyListener voice;
	private static boolean VoiceMode,MultiMode;
	private static Stage stage;
	
	private static Button pauseBtn,exitBtn,cancleBtn;
	private static Thread makeGameThread,voiceThread,gameThread;
	
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
	
	public Game(String title, AnchorPane pane, Scene sc, boolean VoiceMode,boolean MultiMode, Stage stage) {
		this.pane = pane;
		this.title = title;
		this.sc = sc;
		this.VoiceMode= VoiceMode;
		this.MultiMode = MultiMode;
		this.stage=stage;
		score=0;
		combo=0;
		DOSApplicationController.introMusic.silence();
		sc.setOnKeyPressed(new KeyListener(pane, noteList));
		sc.setOnKeyReleased(new NoteEffectKeyListener(pane));
		if(VoiceMode) {
			voiceMode();
		}
		System.out.println(VoiceMode);
		

		
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
		makeGameThread = new Thread(new MakeGameTask(music,pane,noteList));
		makeGameThread.setDaemon(true);
		makeGameThread.start();
		if(VoiceMode) {
			voiceThread = new Thread(voice);
			voiceThread.start();
		}
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
			thread.setName("Game Thread");
			thread.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pause() {
		Task<Void> task = new Task<Void>() {
			public Void call() throws Exception {
				Popup pop =new Popup();
				Platform.runLater(() -> {
					try {
						AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("PopupPause.fxml"));
						pop.getContent().add(second);
						pop.show(stage);
						exitBtn = new Button();
						cancleBtn = new Button();
						exitBtn.setLayoutX(365);
						exitBtn.setLayoutY(239);
						exitBtn.setPrefSize(126, 189);
						exitBtn.setOpacity(0);
						cancleBtn.setLayoutX(87);
						cancleBtn.setLayoutY(239);
						cancleBtn.setPrefSize(186, 189);
						cancleBtn.setOpacity(0);
						
						exitBtn.setOnMouseClicked(e->{pop.hide(); moveHome();});
						cancleBtn.setOnMouseClicked(e->{pop.hide();});
						
						pop.setAutoHide(true);
						
						second.getChildren().add(exitBtn);
						second.getChildren().add(cancleBtn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				
				return null;
			}
		};
		
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	private static void moveHome() {
		music.close();
		makeGameThread.interrupt();
		if(VoiceMode)
			voiceThread.interrupt();
		ScoreBoard.playing=false;
	
		
		ImageStorage.judgeImgView.setImage(null);

		try {

			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("SelectScreen.fxml"));
			LobbyView lobbyView = new LobbyView(second);
			Menubar menubar = new Menubar(second,0);
			MultiThreadClient.roomExit();
			// 씬에 레이아웃 추가
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		DOSApplicationController.introMusic.normalVolume();
		
	}
	

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}
	public static boolean isVoiceMode() {
		return VoiceMode;
		
	}
	
	public static boolean isMultiMode() {
		return MultiMode;
	}
	public static Music getMusic() {
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
