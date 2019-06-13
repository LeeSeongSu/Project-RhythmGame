package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MultiScreenView {
	private AnchorPane pane;
	private Button startBtn, readyBtn;
	private static Button exButton;
	private static ArrayList<Button> buttons;
	private int musicIndex;
	private static boolean start = false;
	private boolean a = true;
	private ArrayList<String> room;
	private ArrayList<String> ready;
	private String text;
	private BackgroundImage startBtnBgImg, readyBtnImg, startBtnEffectImg, readyBtnBgImg, readyBtnBgEffectImg,
			exButtonBlueImg, exButtonRedImg;

	public MultiScreenView(AnchorPane pane, int musicIndex) {
		this.pane = pane;
		this.musicIndex = musicIndex;
		start = false;
		
		room = MultiThreadClient.getRoom();
		ready = MultiThreadClient.getReady();

		Image startBtnImg = (new ImageParser("Wait_start2.png").getImage());

		startBtnBgImg = new BackgroundImage(startBtnImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);

		Image startBtnEffect = (new ImageParser("Wait_start1.png").getImage());

		startBtnEffectImg = new BackgroundImage(startBtnEffect, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);

		Image readyBtnImg = (new ImageParser("Wait_ready2.png").getImage());

		readyBtnBgImg = new BackgroundImage(readyBtnImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);

		Image readyBtnEffectImg = (new ImageParser("Wait_ready1.png").getImage());

		readyBtnBgEffectImg = new BackgroundImage(readyBtnEffectImg, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

		Image exButtonBlue = (new ImageParser("Wait_user1.png").getImage());

		exButtonBlueImg = new BackgroundImage(exButtonBlue, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);

		Image exButtonRed = (new ImageParser("Wait_user2.png").getImage());

		exButtonRedImg = new BackgroundImage(exButtonRed, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);

		startBtn = new Button();
		startBtn.setBackground(new Background(startBtnBgImg));
		startBtn.setLayoutX(286);
		startBtn.setLayoutY(686);
		startBtn.setPrefSize(240, 164);
		startBtn.setOnMouseClicked(e -> clickStartBtn());
		startBtn.setOnMouseEntered(e -> startBtnEnter());
		startBtn.setOnMouseExited(e -> startBtnExit());

		readyBtn = new Button();
		readyBtn.setBackground(new Background(readyBtnBgImg));
		readyBtn.setLayoutX(567);
		readyBtn.setLayoutY(686);
		readyBtn.setPrefSize(240, 164);
		readyBtn.setOnMouseClicked(e -> clickReadyBtn());

		buttons = new ArrayList<Button>();
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());

		for (int i = 0; i < buttons.size(); i++) {

			exButton = buttons.get(i);
			exButton.setBackground(new Background(exButtonBlueImg));
			exButton.setLayoutX(923);
			exButton.setLayoutY(207 + 100 * i);
			exButton.setPrefSize(791, 71);

			pane.getChildren().add(exButton);
			if (i == 0) {
				buttons.get(0).setLayoutX(294);
				buttons.get(0).setLayoutY(407);
				buttons.get(0).setPrefSize(500, 93);

			}
		}

		Thread thread = new Thread() { // fx UI를 변경하는 쓰레드에 접근하기 위해 만든 쓰레드
			@Override
			public void run() {

				while (!start) {
					Platform.runLater(() -> {

						room = MultiThreadClient.getRoom();
						ready = MultiThreadClient.getReady();
						for (int i = 0; i < room.size(); i++) {
							exButton = buttons.get(i);
							text = room.get(i);
							if(ready.size()==0)
								continue;
							if (ready.get(i).equals("1")) {
								readyBtn.setBackground(new Background(readyBtnBgEffectImg));
								exButton.setBackground(new Background(exButtonRedImg));
							} else {
								readyBtn.setBackground(new Background(readyBtnBgImg));
								exButton.setBackground(new Background(exButtonBlueImg));
							}
							exButton.setText(text);
							exButton.setStyle("-fx-text-fill: white; -fx-font-size : 30px;");
						}

						for (int i = room.size(); i < buttons.size(); i++) {
							buttons.get(i).setText("");

						}
					});
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
				Platform.runLater(() -> GameScreen());
			}
		};
		thread.setName("roomUpdateThread");
		thread.setDaemon(true);
		thread.start();
		pane.getChildren().add(startBtn);
		pane.getChildren().add(readyBtn);

	}

	public void clickStartBtn() {

		if (!room.get(0).equals(MultiThreadClient.clientId)) {
			ready();
		} else {

			for (int i = 0; i < ready.size(); i++) {
				if (ready.get(i).equals("0")) {

					JOptionPane.showMessageDialog(null, "모든 플레이어가 준비를 하지 않았습니다.");
					return;
				}
			}

			MultiThreadClient.start();

		}
	}

	public void GameScreen() {
		Stage stage = (Stage) startBtn.getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(Class.forName("application.Main").getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
		
			Game game = null;
			new ScoreBoard(pane); 
			
			stage.show();
			new item(stage);
			
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					Game tmp = game;
					if(LobbyView.mode_voice.equals("voice")) {
						tmp = new Game(SongView.mp3List.get(musicIndex), pane, sc,true,stage);
					}else {
						tmp = new Game(SongView.mp3List.get(musicIndex), pane, sc,false,stage);
					}
					tmp.run();
					return null;
				}
			};
			new VolumeBoard(pane);
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private void startBtnEnter() {

		startBtn.setBackground(new Background(startBtnEffectImg));

	}

	private void startBtnExit() {
		startBtn.setBackground(new Background(startBtnBgImg));

	}

	private void clickReadyBtn() {

		ready();

	}

	public void ready() {
		MultiThreadClient.ready();
	}

	public static void setStart(boolean bool) {
		start = bool;
	}
}