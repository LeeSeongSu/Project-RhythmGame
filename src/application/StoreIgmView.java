package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class StoreIgmView {
	private AnchorPane pane;
	private ImageView Background;
	private static ArrayList<Button> buttons;
	private Button btn;

	public StoreIgmView(AnchorPane pane) {
		this.pane = pane;

		Image backGroundImage = (new ImageParser("Store_IGM.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		buttons = new ArrayList<Button>();

		for (int a = 0; a < 6; a++) {
			if(a<3) {
				btn = new Button();
				btn.setPrefSize(472, 372);
				btn.setLayoutX(443 + a * 470);
				btn.setLayoutY(215);
				btn.setOpacity(1);
			}else {
				btn = new Button();
				btn.setPrefSize(472, 372);
				btn.setLayoutX(443 + (a-3) * 470);
				btn.setLayoutY(590);
				btn.setOpacity(1);
			}
			pane.getChildren().add(btn);

		}

	}

}
