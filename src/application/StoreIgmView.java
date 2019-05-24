package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StoreIgmView {
	private AnchorPane pane;
	private ImageView Background;

	public StoreIgmView(AnchorPane pane) {
		this.pane = pane;

		Image backGroundImage = (new ImageParser("Store_IGM.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
	}

}
