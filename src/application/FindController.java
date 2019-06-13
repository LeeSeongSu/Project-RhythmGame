package application;

import java.io.IOException;
import java.util.HashMap;
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

public class FindController {

	private ImageView Background;
	private TextField email, answer;
	private Label emaillbl, answerlbl;
	private Button signUpBtn, BackBtn;
	private AnchorPane pane;

	public FindController(AnchorPane pane) {
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

		emaillbl = new Label("Email");
		emaillbl.setLayoutX(600);
		emaillbl.setLayoutY(340);
		emaillbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(emaillbl);

		answer = new TextField();
		answer.setMinWidth(430);
		answer.setMinHeight(65);
		answer.setLayoutX(918);
		answer.setLayoutY(425);
		pane.getChildren().add(answer);

		answerlbl = new Label("가장 좋아하는 숫자는?");
		answerlbl.setLayoutX(600);
		answerlbl.setLayoutY(440);
		answerlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(answerlbl);

		signUpBtn = new Button();
		Image signUpImage = (new ImageParser("Login_join.png").getImage());
		BackgroundImage signUpBtnBgImg = new BackgroundImage(signUpImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		signUpBtn.setBackground(new Background(signUpBtnBgImg));
		signUpBtn.setLayoutX(750);
		signUpBtn.setLayoutY(650);
		signUpBtn.setPrefSize(376, 88);
		pane.getChildren().add(signUpBtn);

		signUpBtn.setOnAction(e -> findPassword());

	}

	public void findPassword() {
		if (answer.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "모든 칸을 입력해주세요.");
			return;
		}
		if (!isValidEmail(email.getText())) {
			JOptionPane.showMessageDialog(null, "이메일을 확인해주세요.");
			return;
		}
		Map<String, String> map = new HashMap<>();
		map.put("email", email.getText());
		map.put("answer", answer.getText());
		HttpConnector hc = new HttpConnector("sendMail", map);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					String result = hc.request();
					if (result.equals("Wrong Email or Answer")) {
						JOptionPane.showMessageDialog(null, "옳바르지 않은 메일이거나 답변이 틀리셨습니다.");
					} else if (result.equals("Connect Error")) {
						JOptionPane.showMessageDialog(null, "메일 보내기에 실패하였습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "메일 보내기에 성공하셨습니다.");
						Platform.runLater(() -> moveMain());
					}
				} catch (Exception e) {
					System.out.println("Find Fail");
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
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

}
