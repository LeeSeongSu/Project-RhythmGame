package application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeNoteView {
	private AnchorPane pane;
	private Button btn;
	private ImageView Background;
	public ThemeNoteView(AnchorPane pane) {
		// TODO Auto-generated constructor stub
		this.pane = pane;
		
		Image backGroundImage = (new ImageParser("Theme_Note_bg.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
		// TODO Auto-generated constructor stub
		btn = new Button("1");
		btn.setLayoutX(500);
		btn.setLayoutY(500);
		
		pane.getChildren().add(btn);
	}

}
