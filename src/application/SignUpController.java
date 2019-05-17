package application;



import java.io.IOException;

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
	private TextField id,name,mail;
	private PasswordField pw,rePw; 
	private Text idText,pwText,rePwText,nameText,mailText;
	private Label idlbl,pwlbl,rePwlbl,namelbl,maillbl;
	private Button signUpBtn;
	private AnchorPane pane;
	
	public SignUpController(AnchorPane pane) {
		this.pane=pane;
		
		id=new TextField();
		id.setMinWidth(430);
		id.setMinHeight(65);
		id.setLayoutX(918);
		id.setLayoutY(325);
		pane.getChildren().add(id);
		
		idlbl = new Label("아이디");
		idlbl.setLayoutX(600);
		idlbl.setLayoutY(340);
		idlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(idlbl);
		
		pw= new PasswordField();
		pw.setMinWidth(430);
		pw.setMinHeight(65);
		pw.setLayoutX(918);
		pw.setLayoutY(425);
		pane.getChildren().add(pw);
		
		pwlbl = new Label("비밀번호");
		pwlbl.setLayoutX(600);
		pwlbl.setLayoutY(440);
		pwlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(pwlbl);
		
		pwText=new Text();
		pwText.setLayoutX(918);
		pwText.setLayoutY(500);
		pw.setOnKeyReleased(e->passwordInspect());
		
		rePw= new PasswordField();
		rePw.setMinWidth(430);
		rePw.setMinHeight(65);
		rePw.setLayoutX(918);
		rePw.setLayoutY(525);
		pane.getChildren().add(rePw);
		
		rePwlbl = new Label("비밀번호 확인");
		rePwlbl.setLayoutX(600);
		rePwlbl.setLayoutY(540);
		rePwlbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(rePwlbl);
		
		rePwText=new Text();
		rePwText.setLayoutX(918);
		rePwText.setLayoutY(610);
		rePw.setOnKeyReleased(e->passwordInspect());
		pane.getChildren().add(rePwText);
		
		name= new TextField();
		name.setMinWidth(430);
		name.setMinHeight(65);
		name.setLayoutX(918);
		name.setLayoutY(625);
		pane.getChildren().add(name);
		
		namelbl = new Label("닉네임");
		namelbl.setLayoutX(600);
		namelbl.setLayoutY(640);
		namelbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(namelbl);
		
		mail= new TextField();
		mail.setMinWidth(430);
		mail.setMinHeight(65);
		mail.setLayoutX(918);
		mail.setLayoutY(725);
		pane.getChildren().add(mail);
		
		maillbl = new Label("이메일");
		maillbl.setLayoutX(600);
		maillbl.setLayoutY(740);
		maillbl.setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:white");
		pane.getChildren().add(maillbl);
		
		signUpBtn=new Button();
		Image signUpImage = (new ImageParser("Login_join.png").getImage());
		BackgroundImage signUpBtnBgImg = new BackgroundImage(signUpImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
		signUpBtn.setBackground(new Background(signUpBtnBgImg));
		signUpBtn.setLayoutX(750);
		signUpBtn.setLayoutY(800);
		signUpBtn.setPrefSize(376, 88);
		pane.getChildren().add(signUpBtn);
		
		signUpBtn.setOnAction(e-> signUp());
		
	}
	
	public void passwordInspect() {
		if(pw.getText()==rePw.getText())
			rePwText.setText("");
		else
			rePwText.setText("비밀번호가 일치 하지 않습니다.");
		
	}
	
	public void signUp() {
		UserDto dto=new UserDto(id.getText(),pw.getText(),name.getText(),mail.getText());
		if(new Dao().signUp(dto)==1) {
			moveMain();
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
