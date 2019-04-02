package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * @author 전부
 * MainScreen.fxml의 컨트롤러파일 
 */
public class DOSApplicationController implements Initializable{
	@FXML
	private ImageView backgroundImageView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Image backgroundImage = (new ImageParser("main.gif").getImage());
		backgroundImageView.setImage(backgroundImage);
		Music introMusic = new Music("Game On.mp3", true);
		try {
			Thread.sleep(4500);
			introMusic.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
