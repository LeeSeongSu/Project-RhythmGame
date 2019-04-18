package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageStorage {
	public static Image effectImage = (new ImageParser("NoteEffect.png").getImage());
	public static ImageView[] effectImgView = {new ImageView(effectImage),
			new ImageView(effectImage),new ImageView(effectImage),new ImageView(effectImage),
			new ImageView(effectImage),new ImageView(effectImage),new ImageView(effectImage)};
	public ImageStorage() {
		for(int i=0;i<effectImgView.length;i++) {
			effectImgView[i].setLayoutX(500+i*115);
			effectImgView[i].setLayoutY(310);
		}
	}
}
