package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author 태일 MainScreen.fxml DOSApplication을 컨트롤하는 컨트롤러
 */
public class DOSApplicationController extends Thread implements Initializable {
	@FXML
	private ImageView backgroundImageView;
	@FXML
	private ImageView MainlogoView;// 메인로고

	@FXML
	private Button loginBtn;// 로그인 버튼
	@FXML
	private Button press;// 아무키나 누르시오
	@FXML
	private Group loginscreen;// 로그인 화면

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Image backgroundImage = (new ImageParser("Main_bg.gif").getImage());
		backgroundImageView.setImage(backgroundImage);

		Music introMusic = new Music("Game On.mp3", true);
		try {
			Thread.sleep(4500);
			introMusic.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		loginBtn.setOnAction(e -> movePage());
		loginscreen.setVisible(false);// 로그인창 숨김
	}

	/**
	 * @author 성수(메인화면에서 아무키나 눌렀을 때)
	 */
	public void press() {

		press.setVisible(false);// 아무키나 누르시오 창 숨김
		MainlogoView.setLayoutY(-350);// 메인로고 위치
		loginscreen.setVisible(true);// 로그인창 보임

	}

	/**
	 * @author 재원,성수 페어(페이지 전환)
	 */
	public void movePage() {

		// 새 스테이지 추가

		Stage stage = (Stage) loginBtn.getScene().getWindow();

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("SelectScreen.fxml"));

			Menubar menubar = new Menubar(second);
			// 씬에 레이아웃 추가
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}