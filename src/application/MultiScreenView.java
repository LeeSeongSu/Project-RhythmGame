package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MultiScreenView {
	private AnchorPane pane;
	private Button startBtn;

	public MultiScreenView(AnchorPane pane) {
		this.pane = pane;

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
			AnchorPane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game("Disfigure - Blank.mp3", pane, sc);
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
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}