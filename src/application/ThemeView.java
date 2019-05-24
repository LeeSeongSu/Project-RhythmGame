package application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class ThemeView {

	private AnchorPane pane;
	private ImageView Background;
	private Button Btn;

	public ThemeView(AnchorPane pane) {

		this.pane = pane;

		Image backGroundImage = (new ImageParser("Lobby_image.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		Button btn = new Button("aa");
		btn.setLayoutX(500);
		btn.setLayoutY(500);
		pane.getChildren().add(btn);
	}

}










