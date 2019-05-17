package application;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MultiScreenView {
	private AnchorPane pane;
	private Button startBtn;
	private int musicIndex;

	public MultiScreenView(AnchorPane pane,int musicIndex) {
		this.pane = pane;
		this.musicIndex=musicIndex;
		
		startBtn = new Button("Start");
		startBtn.setPrefSize(164, 92);
		startBtn.setLayoutX(20);
		startBtn.setLayoutY(20);
		startBtn.setOnMouseClicked(e -> GameScreen());
		pane.getChildren().add(startBtn);
	}

	public void GameScreen() {
		Stage stage = (Stage) startBtn.getScene().getWindow();

		try {
			AnchorPane pane = FXMLLoader.load(Class.forName("application.Main").getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game(SongView.musicList.get(musicIndex), pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}