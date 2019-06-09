/**
 * 
 */
package application;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.xs.StringList;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

/**
 * @author 재원(코딩), 성수(로직) 페어 :홈,테마,스토어 버튼 이벤트 추가
 *
 */
public class Menubar {

	private AnchorPane pane;
	private Label money;
	private ImageView Background, select;
	private BackgroundImage selectBtnBgImg;
	private Button homeBtn, themeBtn, storeBtn, btn;
	private static ArrayList<Button> buttons;
	private static ArrayList<String> fxmlList;
	private static ArrayList<String> screenList;

	public Menubar(AnchorPane pane, int i) {
		this.pane = pane;

		Image selectImage = (new ImageParser("Lobby_selectEffect.png").getImage());
		money = new Label(LoginSession.money);
		money.setLayoutX(1740);
		money.setLayoutY(20);
		money.setPrefWidth(135);
		money.setPrefHeight(70);
		money.setAlignment(Pos.BASELINE_RIGHT);
		money.setStyle("-fx-font-size : 38px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(money);
		buttons = new ArrayList<Button>();

		selectBtnBgImg = new BackgroundImage(selectImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);

		for (int a = 0; a < 3; a++) {
			btn = new Button();
			btn.setPrefSize(388, 125);
			btn.setBackground(new Background(selectBtnBgImg));
			btn.setLayoutX(0 + a * 380);
			btn.setLayoutY(0);
			btn.setOpacity(0);

			buttons.add(btn);

			pane.getChildren().add(btn);

		}

		buttons.get(i).setOpacity(1);

		buttons.get(0).setOnMouseClicked(e -> btnClick(0));
		buttons.get(1).setOnMouseClicked(e -> btnClick(1));
		buttons.get(2).setOnMouseClicked(e -> btnClick(2));

		fxmlList = new ArrayList<String>();
		fxmlList.add("HomeScreen.fxml");
		fxmlList.add("ThemeScreen.fxml");
		fxmlList.add("StoreScreen.fxml");

		screenList = new ArrayList<String>();
		screenList.add("StoreView");
		screenList.add("HomeView");
		screenList.add("ThemeView");

	}

	private void btnClick(int i) {
		for (int j = 0; j < buttons.size(); j++) {
			if (i == j)
				buttons.get(j).setOpacity(1);
			else
				buttons.get(j).setOpacity(0);
		}

		Stage stage = (Stage) buttons.get(i).getScene().getWindow();

		try {

			if (fxmlList.get(i) == "StoreScreen.fxml") {
				AnchorPane second = FXMLLoader.load(getClass().getResource(fxmlList.get(i)));
				StoreView storeview = new StoreView(second, true);
				Menubar menubar = new Menubar(second, i);

				Scene sc = new Scene(second);

				stage.setScene(sc);

				stage.show();
			} else if (fxmlList.get(i) == "HomeScreen.fxml") {
				AnchorPane second = FXMLLoader.load(getClass().getResource("SelectScreen.fxml"));
				LobbyView Lobbyview = new LobbyView(second);

				Menubar menubar = new Menubar(second, i);

				Scene sc = new Scene(second);

				stage.setScene(sc);

				stage.show();
			} else {
				AnchorPane second = FXMLLoader.load(getClass().getResource("ThemeScreen.fxml"));
				ThemeView themeView = new ThemeView(second);

				Menubar menubar = new Menubar(second, i);

				Scene sc = new Scene(second);

				stage.setScene(sc);

				stage.show();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}