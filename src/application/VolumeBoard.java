package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class VolumeBoard {

	static boolean playing;

	public VolumeBoard(Pane pane) {

		
		playing=true;
		Image volumeBoardImage = new ImageParser("Volume.png").getImage();
		ImageView volumeBoard = new ImageView(volumeBoardImage);
		volumeBoard.setLayoutX(1500);
		volumeBoard.setLayoutY(100);
		

		Rectangle line = new Rectangle(0, 613);//613
		line.setStroke(Paint.valueOf("#FFFFFF"));
		line.setStrokeWidth(10.5);
		line.setLayoutX(1576);
		line.setLayoutY(162);
		line.setStrokeType(StrokeType.OUTSIDE);
		pane.getChildren().add(line);
		
		Rectangle block = new Rectangle(0, 613);
		block.setStroke(Paint.valueOf("#000000"));
		block.setStrokeWidth(10.5);
		block.setLayoutX(1576);
		block.setLayoutY(162);
		block.setStrokeType(StrokeType.OUTSIDE);
		
		pane.getChildren().add(block);
		
		pane.getChildren().add(volumeBoard);
		Thread thread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(playing) {
					
					if(VoiceKeyListener.voiceFreq<201&&VoiceKeyListener.voiceFreq>5)
						block.setHeight(VoiceKeyListener.voiceFreq*2.5);
					else
						block.setHeight(613);
				}
			}
		};
		
		thread.setName("VolumeBar");
        thread.setDaemon(true);
        thread.start();
	}

}

