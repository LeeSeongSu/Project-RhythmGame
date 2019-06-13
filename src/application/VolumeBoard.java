package application;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VolumeBoard {

	public VolumeBoard(Pane pane) {
	
		Image volumeBoardImage = new ImageParser("Volume.png").getImage();
		ImageView volumeBoard = new ImageView(volumeBoardImage);
		volumeBoard.setLayoutX(1500);
		volumeBoard.setLayoutY(100);
		pane.getChildren().add(volumeBoard);
		
	}

}
