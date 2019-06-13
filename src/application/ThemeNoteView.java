package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeNoteView {
	private AnchorPane pane;
	private Button btn;
	private ImageView Background;
	private ArrayList<Button> buttons1;

	public ThemeNoteView(AnchorPane pane) {
		// TODO Auto-generated constructor stub
		this.pane = pane;

		Image backGroundImage = (new ImageParser("Theme_Note_bg.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
		// TODO Auto-generated constructor stub

		buttons1 = new ArrayList<Button>();
		for (int a = 0; a < 3; a++) {
			btn = new Button();
			btn.setPrefSize(480, 818);

			btn.setLayoutX(433 + a * 480);
			btn.setLayoutY(208);
			btn.setOpacity(0);

			buttons1.add(btn);

			pane.getChildren().add(btn);

		}
		Menubar menubar = new Menubar(pane, 1);
	}

}
