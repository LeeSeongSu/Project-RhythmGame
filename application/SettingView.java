package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingView {

	private AnchorPane pane;

	private Button bgSoundLeftBtn, bgSoundRightBtn, effectLeftBtn, effectRightBtn, reviseBtn;

	private TextField id;

	private PasswordField passWord;

	private Button exitBtn;

	private BackgroundImage homeBtnBgImg;

	private ImageView homeBtnImgView;

	public SettingView(AnchorPane pane) {

		this.pane = pane;

		Image homeImage = new ImageParser("HomeBtn.png").getImage();

		homeBtnBgImg = new BackgroundImage(homeImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,

				BackgroundPosition.CENTER, null);

		bgSoundLeftBtn = new Button();

		bgSoundLeftBtn.setPrefSize(92, 88);

		bgSoundLeftBtn.setLayoutX(267);

		bgSoundLeftBtn.setLayoutY(422);

		bgSoundLeftBtn.setOpacity(0.5);
		
		bgSoundLeftBtn.setOnMouseClicked(e -> volumeDown());

		pane.getChildren().add(bgSoundLeftBtn);

		bgSoundRightBtn = new Button();

		bgSoundRightBtn.setPrefSize(92, 88);

		bgSoundRightBtn.setLayoutX(672);

		bgSoundRightBtn.setLayoutY(422);

		bgSoundRightBtn.setOpacity(0.5);
		
		bgSoundRightBtn.setOnMouseClicked(e -> volumeUp());

		pane.getChildren().add(bgSoundRightBtn);

		effectLeftBtn = new Button();

		effectLeftBtn.setPrefSize(92, 88);

		effectLeftBtn.setLayoutX(267);

		effectLeftBtn.setLayoutY(817);

		effectLeftBtn.setOpacity(0.5);

		pane.getChildren().add(effectLeftBtn);

		effectRightBtn = new Button();

		effectRightBtn.setPrefSize(92, 88);

		effectRightBtn.setLayoutX(673);

		effectRightBtn.setLayoutY(816);

		effectRightBtn.setOpacity(0.5);

		pane.getChildren().add(effectRightBtn);

		reviseBtn = new Button();

		reviseBtn.setPrefSize(174, 262);

		reviseBtn.setLayoutX(1689);

		reviseBtn.setLayoutY(519);

		reviseBtn.setOpacity(0.5);
		
		reviseBtn.setOnMouseClicked(e->changeInfo());

		pane.getChildren().add(reviseBtn);

		id = new TextField();

		id.setPrefSize(373, 71);

		id.setFont(Font.font("210 OmniGothic 020"));

		id.setLayoutX(1256);

		id.setLayoutY(489);

		id.setOpacity(0.5);

		pane.getChildren().add(id);

		passWord = new PasswordField();

		passWord.setPrefSize(373, 71);

		passWord.setLayoutX(1256);

		passWord.setLayoutY(612);

		passWord.setOpacity(0.5);

		pane.getChildren().add(passWord);

		homeBtnImgView = new ImageView(homeImage);

		homeBtnImgView.setFitHeight(193);

		homeBtnImgView.setFitWidth(164);

		homeBtnImgView.setLayoutX(1705);

		homeBtnImgView.setLayoutY(32);

		pane.getChildren().add(homeBtnImgView);

		exitBtn = new Button();

		exitBtn.setPrefSize(164, 193);

		exitBtn.setLayoutX(1705);

		exitBtn.setLayoutY(32);

		exitBtn.setStyle("-fx-background-color: TRANSPARENT");

		exitBtn.setOnMouseClicked(e -> moveMain());

		pane.getChildren().add(exitBtn);

	}
	

	public void changeInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", id.getText());
		map.put("password", passWord.getText());
		map.put("memberId",LoginSession.memberId);
		
		HttpConnector hc = new HttpConnector("changeInfo", map);
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					String result = hc.request();
					if (result.equals("Email Error")) {
						JOptionPane.showMessageDialog(null, "이메일을 확인해주세요.");
					} else if(result.equals("Error")){
						JOptionPane.showMessageDialog(null, "인터넷 연결을 확인해주세요.");
					}else {
						JOptionPane.showMessageDialog(null, "변경 성공하였습니다.");
					}
				} catch (Exception e) {
					System.out.println("login Fail");
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}


	public void moveMain() {

		// 새 스테이지 추가

		Stage stage = (Stage) exitBtn.getScene().getWindow();

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("/view/SelectScreen.fxml"));

			LobbyView lobbyView = new LobbyView(second);

			Menubar menubar = new Menubar(second, 0);

			// 씬에 레이아웃 추가

			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	public static void changeVolume(double changeValue) {
		double volume=DOSApplicationController.introMusic.getVolume()+changeValue;
		if(volume>0) {
			if(volume<=1)
			DOSApplicationController.introMusic.setVolume(volume);
		}
	}
	public void volumeUp() {
		SettingView.changeVolume(0.1);
	}
	
	public void volumeDown() {
		SettingView.changeVolume(-0.1);
	}
	
}


