package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ThemeView {

	private AnchorPane pane;
	private ImageView Background;
	private Button btn;
	private static ArrayList<Button> buttons;
	private static ArrayList<String> fxmlList;
	private static ArrayList<String> screenList;
	private boolean isFirst;

	public ThemeView(AnchorPane pane, boolean isFirst) {
		this.isFirst = isFirst;
		this.pane = pane;
		if(isFirst) {
			Image backGroundImage = (new ImageParser("Theme_bg.png").getImage());
			Background = new ImageView(backGroundImage);
			pane.getChildren().add(Background);// 로비 배경
		
		}
		buttons = new ArrayList<Button>();

		for (int a = 0; a < 2; a++) {
			btn = new Button();
			btn.setPrefSize(287, 87);

			btn.setLayoutX(100);
			btn.setLayoutY(463 + a * 160);
			btn.setOpacity(0);
			buttons.add(btn);

			pane.getChildren().add(btn);
			
		}
		
	

		
		buttons.get(0).setOnMouseClicked(e -> btnClick1(0));
		buttons.get(1).setOnMouseClicked(e -> btnClick1(1));

		
		fxmlList = new ArrayList<String>();
		
		fxmlList.add("ThemeGameScreen.fxml");
		fxmlList.add("ThemeNoteScreen.fxml");

		screenList = new ArrayList<String>();
	
		screenList.add("ThemeGameView");
		screenList.add("ThemeNoteView");
	}

	private void btnClick1(int i) {

		Stage stage = (Stage) buttons.get(i).getScene().getWindow();

		try {

			
			if (fxmlList.get(i) == "ThemeGameScreen.fxml") {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("ThemeGameScreen.fxml"));
				new ThemeGameView(nextScreen);
				new Menubar(nextScreen, 1);
				new ThemeView(nextScreen, false);
				Scene sc = new Scene(nextScreen);
				stage.setScene(sc);

				stage.show();
			} else {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("ThemeNoteScreen.fxml"));
				new ThemeNoteView(nextScreen);
				new Menubar(nextScreen, 1);
				new ThemeView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
