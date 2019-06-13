package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StoreNoteView {
	private AnchorPane pane;
	private ImageView Background;
	private ArrayList<Button> buttons;
	private Button btn;
	public StoreNoteView(AnchorPane pane) {
		// TODO Auto-generated constructor stub
		this.pane = pane;
		Image backGroundImage = (new ImageParser("Store_Note.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
		
		buttons = new ArrayList<Button>();
		for (int a = 0; a < 3; a++) {
			btn = new Button();
			btn.setPrefSize(480, 818);

			btn.setLayoutX(433 + a * 480);
			btn.setLayoutY(208);
			btn.setOpacity(0);

			buttons.add(btn);

			pane.getChildren().add(btn);

		}
		Menubar menubar = new Menubar(pane, 2);
	}

}
