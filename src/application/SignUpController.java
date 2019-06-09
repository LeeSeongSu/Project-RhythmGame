package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpController {

	private ImageView Background;
	private TextField email, name, answer;
	private PasswordField pw, rePw;
	private Text emailText, pwText, rePwText, nameText, answerText;
	private Label emailbl, pwlbl, rePwlbl, namelbl, answerlbl;
	private Button signUpBtn, BackBtn;
	private AnchorPane pane;

	public SignUpController(AnchorPane pane) {
		this.pane = pane;

		BackBtn = new Button("Back");
		BackBtn.setLayoutX(112);
		BackBtn.setLayoutY(110);
		BackBtn.setPrefSize(231, 130);
		BackBtn.setOnMouseClicked(e -> BackPage());
		BackBtn.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:black");
		pane.getChildren().add(BackBtn);

		email = new TextField();
		email.setMinWidth(430);
		email.setMinHeight(65);
		email.setLayoutX(918);
		email.setLayoutY(325);
		pane.getChildren().add(email);

		emailbl = new Label("Email");
		emailbl.setLayoutX(600);
		emailbl.setLayoutY(340);
		emailbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(emailbl);

		pw = new PasswordField();
		pw.setMinWidth(430);
		pw.setMinHeight(65);
		pw.setLayoutX(918);
		pw.setLayoutY(425);
		pane.getChildren().add(pw);

		pwlbl = new Label("Password");
		pwlbl.setLayoutX(600);
		pwlbl.setLayoutY(440);
		pwlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(pwlbl);

		pwText = new Text();
		pwText.setLayoutX(918);
		pwText.setLayoutY(500);

		rePw = new PasswordField();
		rePw.setMinWidth(430);
		rePw.setMinHeight(65);
		rePw.setLayoutX(918);
		rePw.setLayoutY(525);
		pane.getChildren().add(rePw);

		rePwlbl = new Label("Confirm Password");
		rePwlbl.setLayoutX(600);
		rePwlbl.setLayoutY(540);
		rePwlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(rePwlbl);

		rePwText = new Text();
		rePwText.setLayoutX(918);
		rePwText.setLayoutY(610);
		pane.getChildren().add(rePwText);

		name = new TextField();
		name.setMinWidth(430);
		name.setMinHeight(65);
		name.setLayoutX(918);
		name.setLayoutY(625);
		pane.getChildren().add(name);

		namelbl = new Label("Nickname");
		namelbl.setLayoutX(600);
		namelbl.setLayoutY(640);
		namelbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(namelbl);

		answer = new TextField();
		answer.setMinWidth(430);
		answer.setMinHeight(65);
		answer.setLayoutX(918);
		answer.setLayoutY(725);
		pane.getChildren().add(answer);

		answerlbl = new Label("가장 좋아하는 숫자는?");
		answerlbl.setLayoutX(600);
		answerlbl.setLayoutY(740);
		answerlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(answerlbl);

		signUpBtn = new Button();
		Image signUpImage = (new ImageParser("Login_join.png").getImage());
		BackgroundImage signUpBtnBgImg = new BackgroundImage(signUpImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		signUpBtn.setBackground(new Background(signUpBtnBgImg));
		signUpBtn.setLayoutX(750);
		signUpBtn.setLayoutY(850);
		signUpBtn.setPrefSize(376, 88);
		pane.getChildren().add(signUpBtn);

		signUpBtn.setOnAction(e -> signUp());

	}

	public boolean passwordInspect() {
		if (pw.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			return false;
		}
		if (pw.getText().equals(rePw.getText())) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
			return false;
		}

	}

	public void signUp() {
		if (name.getText().isEmpty()||answer.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "모든 칸을 입력해주세요.");
			return;
		}
		if (!isValidEmail(email.getText())) {
			JOptionPane.showMessageDialog(null, "이메일을 확인해주세요.");
			return;
		}
		if (passwordInspect()) {
			Map<String, String> map = new HashMap<>();
			map.put("email", email.getText());
			map.put("password", pw.getText());
			map.put("nickname", name.getText());
			map.put("answer", answer.getText());
			HttpConnector hc = new HttpConnector("signup", map);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						String result = hc.request();
						if (result.equals("Duplicate Email")) {
							JOptionPane.showMessageDialog(null, "중복된 이메일입니다.");
						} else {
							JOptionPane.showMessageDialog(null, "회원가입 성공하셨습니다.");
							Platform.runLater(() -> moveMain());
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
	}

	public void BackPage() {
		Stage stage = (Stage) BackBtn.getScene().getWindow();

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public void moveMain() {

		// 새 스테이지 추가

		Stage stage = (Stage) signUpBtn.getScene().getWindow();

		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public boolean isValidEmail(String email) {
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (m.matches()) {
			err = true;
		}
		return err;
	}
}
