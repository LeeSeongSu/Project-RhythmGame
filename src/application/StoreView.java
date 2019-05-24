package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StoreView {

	private AnchorPane pane;
	private ImageView Background;
	private Button btn1;
	private static ArrayList<Button> buttons1;
	private static ArrayList<String> fxmlList1;
	private static ArrayList<String> screenList1;
	private boolean isFirst;

	public StoreView(AnchorPane pane, boolean isFirst) {
		this.isFirst = isFirst;
		this.pane = pane;
		if(isFirst) {
			Image backGroundImage = (new ImageParser("Store_IGM.png").getImage());
			Background = new ImageView(backGroundImage);
			pane.getChildren().add(Background);// 로비 배경
		}
		buttons1 = new ArrayList<Button>();

		for (int a = 0; a < 3; a++) {
			btn1 = new Button();
			btn1.setPrefSize(287, 87);

			btn1.setLayoutX(96);
			btn1.setLayoutY(398 + a * 160);
			btn1.setOpacity(0);
			buttons1.add(btn1);

			pane.getChildren().add(btn1);

		}
		buttons1.get(0).setOnMouseClicked(e -> btnClick1(0));
		buttons1.get(1).setOnMouseClicked(e -> btnClick1(1));
		buttons1.get(2).setOnMouseClicked(e -> btnClick1(2));

		fxmlList1 = new ArrayList<String>();
		fxmlList1.add("StoreIgmScreen.fxml");
		fxmlList1.add("StoreGameScreen.fxml");
		fxmlList1.add("StoreNoteScreen.fxml");

		screenList1 = new ArrayList<String>();
		screenList1.add("StoreIgmView");
		screenList1.add("StoreGameView");
		screenList1.add("StoreNoteView");
	}

	private void btnClick1(int i) {

		Stage stage = (Stage) buttons1.get(i).getScene().getWindow();

		try {

			if (fxmlList1.get(i) == "StoreIgmScreen.fxml") {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("StoreIgmScreen.fxml"));
				new StoreIgmView(nextScreen);
				new Menubar(nextScreen, 2);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			} else if (fxmlList1.get(i) == "StoreGameScreen.fxml") {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("StoreGameScreen.fxml"));
				new StoreGameView(nextScreen);
				new Menubar(nextScreen, 2);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			} else {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("StoreNoteScreen.fxml"));
				new StoreNoteView(nextScreen);
				new Menubar(nextScreen, 2);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
