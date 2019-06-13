package application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeGameView {

	private Button btn;
	private AnchorPane pane;
	private ImageView Background;
	public ThemeGameView(AnchorPane pane) {
		
		this.pane = pane;
		
		Image backGroundImage = (new ImageParser("Theme_bg.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
		// TODO Auto-generated constructor stub
		btn = new Button("1");
		btn.setLayoutX(10);
		btn.setLayoutY(10);
		
		pane.getChildren().add(btn);
	}

}
