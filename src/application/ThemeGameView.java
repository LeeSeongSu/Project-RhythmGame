package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeGameView {
	private AnchorPane pane;
	private ImageView Background;
	private Button btn;
	private static ArrayList<Button> buttons;

	public ThemeGameView(AnchorPane pane) {
		// TODO Auto-generated constructor stub

		this.pane = pane;

		Image backGroundImage = (new ImageParser("Store_Game.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		buttons = new ArrayList<Button>();
		for (int a = 0; a < 3; a++) {
			btn = new Button();
			btn.setPrefSize(481, 835);

			btn.setLayoutX(432 + a * 480);
			btn.setLayoutY(190);
			btn.setOpacity(1);

			buttons.add(btn);

			pane.getChildren().add(btn);

		}
	}

}
