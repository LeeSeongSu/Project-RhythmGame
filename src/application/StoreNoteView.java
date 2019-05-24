package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StoreNoteView {
	private AnchorPane pane;
	private ImageView Background;

	public StoreNoteView(AnchorPane pane) {
		// TODO Auto-generated constructor stub
		this.pane = pane;

		Image backGroundImage = (new ImageParser("Store_Note.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
	}

}
