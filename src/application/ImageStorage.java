package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageStorage {
	public static Image effectImage = (new ImageParser("NoteEffect.png").getImage());
	public static Image badImage = (new ImageParser("BadEffect.png").getImage());
	public static Image greatImage = (new ImageParser("GreatEffect.png").getImage());
	public static Image goodImage = (new ImageParser("GoodEffect.png").getImage());
	public static Image missImage = (new ImageParser("MissEffect.png").getImage());
	public static Image perfectImage = (new ImageParser("PerfectEffect.png").getImage());
	public static ImageView judgeImgView = new ImageView();
	public static ImageView[] effectImgView = {new ImageView(effectImage),
			new ImageView(effectImage),new ImageView(effectImage),new ImageView(effectImage),
			new ImageView(effectImage),new ImageView(effectImage),new ImageView(effectImage)};
	public ImageStorage() {
		judgeImgView.setLayoutX(695);
		judgeImgView.setLayoutY(645);
		for(int i=0;i<effectImgView.length;i++) {
			effectImgView[i].setLayoutX(500+i*115);
			effectImgView[i].setLayoutY(310);
		}
	}
}
