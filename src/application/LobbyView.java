package application;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LobbyView {

	private AnchorPane pane;
	private ImageView Background, start, expBar, expGage;
	private BackgroundImage multiBtnBgImg, multiBtnBgEffectImg, singleBtnBgImg, singleBtnBgEffectImg, onBtnBgImg,
			onBtnBgEffectImg, offBtnBgImg, offBtnBgEffectImg;
	private Button multiBtn, singleBtn, onBtn, offBtn, settingBtn;
	private Label levelLabel, nicknameLabel, expLabel;

	public static String mod = "Single";
	public static String mode_voice = "none";

	public LobbyView(AnchorPane pane) {

		this.pane = pane;

		Image backGroundImage = (new ImageParser("Lobby.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		Image singleImage = (new ImageParser("Lobby_single.png").getImage());
		Image singleEffectImage = (new ImageParser("Lobby_singleEffect.png").getImage());
		Image multiImage = (new ImageParser("Lobby_multi.png").getImage());
		Image multiEffectImage = (new ImageParser("Lobby_multiEffect.png").getImage());
		Image onImage = (new ImageParser("Lobby_on.png").getImage());
		Image onEffectImage = (new ImageParser("Lobby_onEffect.png").getImage());
		Image offImage = (new ImageParser("Lobby_off.png").getImage());
		Image offEffectImage = (new ImageParser("Lobby_offEffect.png").getImage());
		Image startImage = (new ImageParser("Lobby_start.png").getImage());
		Image expBarImage = (new ImageParser("expbar.png").getImage());
		Image expGageImage = (new ImageParser("expgage.png").getImage());
		multiBtnBgImg = new BackgroundImage(multiImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		multiBtnBgEffectImg = new BackgroundImage(multiEffectImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		singleBtnBgImg = new BackgroundImage(singleImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		singleBtnBgEffectImg = new BackgroundImage(singleEffectImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		onBtnBgImg = new BackgroundImage(onImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		onBtnBgEffectImg = new BackgroundImage(onEffectImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		offBtnBgImg = new BackgroundImage(offImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		offBtnBgEffectImg = new BackgroundImage(offEffectImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		if(!LoginSession.chooseNote.equals("0")) {
			int tmp = Integer.parseInt(LoginSession.chooseNote)-3;
			ImageStorage.noteBasicImage = (new ImageParser("Note"+tmp+".png").getImage());
		}
		start = new ImageView(startImage);
		start.setLayoutX(1330);
		start.setLayoutY(870);

		start.setOnMouseEntered(e -> startBtnEnter());
		start.setOnMouseExited(e -> startBtnExit());
		start.setOnMouseClicked(e -> songViewPage());
		pane.getChildren().add(start);// 스타트 사진

		multiBtn = new Button();
		multiBtn.setPrefSize(164, 92);
		multiBtn.setBackground(new Background(multiBtnBgImg));
		multiBtn.setLayoutX(1635);
		multiBtn.setLayoutY(532);
		multiBtn.setOnMouseClicked(e -> multiBtnClick());
		pane.getChildren().add(multiBtn);

		singleBtn = new Button();
		singleBtn.setPrefSize(164, 92);
		singleBtn.setBackground(new Background(singleBtnBgEffectImg));
		singleBtn.setLayoutX(1465);
		singleBtn.setLayoutY(532);
		singleBtn.setOnMouseClicked(e -> singleBtnClick());
		pane.getChildren().add(singleBtn);

		onBtn = new Button();

		onBtn.setPrefSize(164, 92);
		onBtn.setBackground(new Background(onBtnBgImg));
		onBtn.setLayoutX(1475);
		onBtn.setLayoutY(740);
		onBtn.setOnMouseClicked(e -> onBtnClick());
		pane.getChildren().add(onBtn);

		offBtn = new Button();
		offBtn.setPrefSize(164, 92);
		offBtn.setBackground(new Background(offBtnBgEffectImg));
		offBtn.setLayoutX(1635);
		offBtn.setLayoutY(740);
		offBtn.setOnMouseClicked(e -> offBtnClick());
		pane.getChildren().add(offBtn);

		nicknameLabel = new Label(LoginSession.nickname);
		nicknameLabel.setLayoutX(200);
		nicknameLabel.setLayoutY(360);
		nicknameLabel.setFont(Font.font("210 OmniGothic 020", 50));
		nicknameLabel.setStyle("-fx-font-size : 50px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(nicknameLabel);

		levelLabel = new Label("Lv : " + LoginSession.level);
		levelLabel.setLayoutX(200);
		levelLabel.setLayoutY(460);
		levelLabel.setFont(Font.font("210 OmniGothic 020", 50));
		levelLabel.setStyle("-fx-font-size : 50px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(levelLabel);

		expLabel = new Label("Exp : " + LoginSession.exp + "/1000");
		expLabel.setLayoutX(200);
		expLabel.setLayoutY(560);
		expLabel.setFont(Font.font("210 OmniGothic 020", 50));
		expLabel.setStyle("-fx-font-size : 50px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(expLabel);

		Double gage = (double) (482.0 / 1000 * Integer.parseInt(LoginSession.exp));
		expGage = new ImageView(expGageImage);
		expGage.setLayoutX(202);
		expGage.setLayoutY(660);
		if (gage == 0) {
			expGage.setFitWidth(1);
		} else {
			expGage.setFitWidth(gage);
		}
		pane.getChildren().add(expGage);

		expBar = new ImageView(expBarImage);
		expBar.setLayoutX(200);
		expBar.setLayoutY(660);
		pane.getChildren().add(expBar);

		settingBtn = new Button();
		settingBtn.setPrefSize(85, 83);
		settingBtn.setLayoutX(90);
		settingBtn.setLayoutY(897);
		settingBtn.setOnMouseClicked(e -> settingBtnClick());
		settingBtn.setOpacity(0.5);
		pane.getChildren().add(settingBtn);

	}

	private void multiBtnClick() {// 멀티버튼
		if (multiBtn.getBackground().getImages().isEmpty())
			return;
		if (multiBtn.getBackground().getImages().get(0).equals(multiBtnBgEffectImg))
			return;
		multiBtn.setBackground(new Background(multiBtnBgEffectImg));
		singleBtn.setBackground(new Background(singleBtnBgImg));
		mod = "Multi";
	}

	private void singleBtnClick() {// 싱글버튼
		if (singleBtn.getBackground().getImages().isEmpty())
			return;
		if (singleBtn.getBackground().getImages().get(0).equals(singleBtnBgEffectImg))
			return;
		multiBtn.setBackground(new Background(multiBtnBgImg));
		singleBtn.setBackground(new Background(singleBtnBgEffectImg));
		mod = "Single";
	}

	private void onBtnClick() {// on
		if (onBtn.getBackground().getImages().isEmpty())
			return;
		if (onBtn.getBackground().getImages().get(0).equals(onBtnBgEffectImg))
			return;
		onBtn.setBackground(new Background(onBtnBgEffectImg));
		offBtn.setBackground(new Background(offBtnBgImg));
		mode_voice = "voice";
	}

	private void offBtnClick() {// off
		if (offBtn.getBackground().getImages().isEmpty())
			return;
		if (offBtn.getBackground().getImages().get(0).equals(offBtnBgEffectImg))
			return;
		onBtn.setBackground(new Background(onBtnBgImg));
		offBtn.setBackground(new Background(offBtnBgEffectImg));
		mode_voice = "none";
	}

	private void startBtnExit() {
		start.setImage(new ImageParser("Lobby_start.png").getImage());

	}

	private void startBtnEnter() {
		start.setImage(new ImageParser("Lobby_startEffect.png").getImage());

	}

	public void settingBtnClick() {
		Stage stage = (Stage) settingBtn.getScene().getWindow();
		try {

			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("SettingScreen.fxml"));

			SettingView settingview = new SettingView(second);
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void songViewPage() {

		// 새 스테이지 추가

		Stage stage = (Stage) start.getScene().getWindow();

		try {

			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("SelectScreen.fxml"));

			SongView songview = new SongView(second);
			Menubar menubar = new Menubar(second, 0);
			// 씬에 레이아웃 추가
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}