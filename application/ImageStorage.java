package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageStorage {
	public static Image comboWordImage = (new ImageParser("combo.png").getImage());
	public static Image noteBasicImage = (new ImageParser("note.png").getImage());
	public static Image effectImage = (new ImageParser("NoteEffect.png").getImage());
	public static Image badImage = (new ImageParser("BadEffect.png").getImage());
	public static Image greatImage = (new ImageParser("GreatEffect.png").getImage());
	public static Image goodImage = (new ImageParser("GoodEffect.png").getImage());
	public static Image missImage = (new ImageParser("MissEffect.png").getImage());
	public static Image perfectImage = (new ImageParser("PerfectEffect.png").getImage());
	public static ImageView comboWordImageView = new ImageView(comboWordImage);
	public static ImageView judgeImgView = new ImageView();
	public static ImageView[] effectImgView = {new ImageView(effectImage),
			new ImageView(effectImage),new ImageView(effectImage),new ImageView(effectImage),
			new ImageView(effectImage),new ImageView(effectImage),new ImageView(effectImage)};
	
	public static Image comboImage[] = {new ImageParser("c0.png").getImage(),
			new ImageParser("c1.png").getImage(),new ImageParser("c2.png").getImage(),
			new ImageParser("c3.png").getImage(),new ImageParser("c4.png").getImage(),
			new ImageParser("c5.png").getImage(),new ImageParser("c6.png").getImage(),
			new ImageParser("c7.png").getImage(),new ImageParser("c8.png").getImage(),
			new ImageParser("c9.png").getImage()
	};
	
	public static ImageView comboImageView[][] = {{new ImageView(comboImage[0]),
			new ImageView(comboImage[1]),new ImageView(comboImage[2]),
			new ImageView(comboImage[3]),new ImageView(comboImage[4]),
			new ImageView(comboImage[5]),new ImageView(comboImage[6]),
			new ImageView(comboImage[7]),new ImageView(comboImage[8]),
			new ImageView(comboImage[9])},
			{new ImageView(comboImage[0]),
				new ImageView(comboImage[1]),new ImageView(comboImage[2]),
				new ImageView(comboImage[3]),new ImageView(comboImage[4]),
				new ImageView(comboImage[5]),new ImageView(comboImage[6]),
				new ImageView(comboImage[7]),new ImageView(comboImage[8]),
				new ImageView(comboImage[9])}
			,{new ImageView(comboImage[0]),
				new ImageView(comboImage[1]),new ImageView(comboImage[2]),
				new ImageView(comboImage[3]),new ImageView(comboImage[4]),
				new ImageView(comboImage[5]),new ImageView(comboImage[6]),
				new ImageView(comboImage[7]),new ImageView(comboImage[8]),
				new ImageView(comboImage[9])}
			,{new ImageView(comboImage[0]),
				new ImageView(comboImage[1]),new ImageView(comboImage[2]),
				new ImageView(comboImage[3]),new ImageView(comboImage[4]),
				new ImageView(comboImage[5]),new ImageView(comboImage[6]),
				new ImageView(comboImage[7]),new ImageView(comboImage[8]),
				new ImageView(comboImage[9])}
	};
	
	public ImageStorage() {
		judgeImgView.setLayoutX(755);
		judgeImgView.setLayoutY(750);
		comboWordImageView.setLayoutX(810);
		comboWordImageView.setLayoutY(35);
		comboWordImageView.setFitHeight(57);
		comboWordImageView.setFitWidth(300);
		for(int i=0;i<effectImgView.length;i++) {
			effectImgView[i].setLayoutX(500+i*115);
			effectImgView[i].setLayoutY(310);
		}
		for(int i=0;i<comboImageView.length;i++) {
			for(int j = 0; j<comboImageView[i].length;j++) {
				comboImageView[i][j].setFitWidth(80);
				comboImageView[i][j].setFitHeight(80);
			}
		}
	}
}
