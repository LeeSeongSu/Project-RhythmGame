package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeGameView {
	private Button btn;
	private AnchorPane pane;
	private ImageView Background;
	private static ArrayList<Button> buttons1;

	public ThemeGameView(AnchorPane pane) {

		this.pane = pane;

		Image backGroundImage = (new ImageParser("Theme_bg.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		buttons1 = new ArrayList<Button>();
		for (int a = 0; a < 3; a++) {
			btn = new Button();
			btn.setPrefSize(481, 835);

			btn.setLayoutX(432 + a * 480);
			btn.setLayoutY(190);
			btn.setOpacity(0);

			buttons1.add(btn);

			pane.getChildren().add(btn);
		}
		Menubar menubar = new Menubar(pane, 1);
	}

}
