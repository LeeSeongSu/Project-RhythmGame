package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author 태일 MainScreen.fxml DOSApplication을 컨트롤하는 컨트롤러
 */
public class DOSApplicationController extends Thread implements Initializable {
	@FXML
	private ImageView backgroundImageView, mainStartImageView;
	@FXML
	private ImageView mainLogoView;// 메인로고
	

	@FXML
	private Button loginBtn,SignUpBtn;// 로그인 ,회원가입 버튼
	@FXML
	private Group loginscreen;// 로그인 화면
	@FXML
	private TextField idTextField;
	@FXML
	private TextField passwordTextField;

	private boolean twinkleStop = false;
	
	private static boolean visited = false; 
	
	public static Music introMusic;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Image backgroundImage = (new ImageParser("Main_bg.gif").getImage());
		backgroundImageView.setImage(backgroundImage);
		
		loginBtn.setOnAction(e-> loginBeta());
		SignUpBtn.setOnAction(e-> moveSignUp());
		
		if(!visited) {
			introMusic = new Music("Game On.mp3", true);
			try {
	//			Thread.sleep(4500);
				introMusic.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			loginscreen.setVisible(false);// 로그인창 숨김
			changeOpacity(mainStartImageView);
			mainStartImageView.setOnMouseClicked(e -> pressAnyKey());
			visited=true;
		}
		else {;
			pressAnyKey();
		}

		loginscreen.setVisible(false);// 로그인창 숨김
		changeOpacity(mainStartImageView);
		mainStartImageView.setOnMouseClicked(e -> pressAnyKey());
	}

	public void changeOpacity(ImageView imageView) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				double opacity = 1;
				boolean decent = true;
				while (!twinkleStop) {
					if (decent) {
						opacity = opacity - 0.01;
						imageView.setOpacity(opacity);
						if (opacity < 0)
							decent = false;
					} else {
						opacity = opacity + 0.01;
						imageView.setOpacity(opacity);
						if (opacity > 1)
							decent = true;
					}
					try {
						Thread.sleep(30);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * @author 태일
	 */
	public void pressAnyKey() {
		twinkleStop = true;
		mainStartImageView.setVisible(false);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if(!visited) {
					double opacity = 1;
					while(opacity>0) {
						opacity = opacity - 0.01;
						mainLogoView.setOpacity(opacity);
						Thread.sleep(30);
					}
				}
				mainLogoView.setVisible(false);
				loginscreen.setVisible(true);// 로그인창 보임
				return null;
			}
		};

		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	public void login() {
		String email = idTextField.getText();
		String password = passwordTextField.getText();
		
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		HttpConnector hc = new HttpConnector("login", map);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try{
					Map<String, String> result = hc.request();
					LoginSession.email=result.get("email");
					LoginSession.token=result.get("token");
					LoginSession.nickname=result.get("nickname");
					MultiThreadClient.clientId=idTextField.getText();
					MultiThreadClient.sendID(MultiThreadClient.clientId);
					System.out.println(LoginSession.nickname);
					Platform.runLater(()->moveLobby());
				}catch (Exception e) {
					System.out.println("login Fail");
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	/**
	 * @author 재원,성수 페어(페이지 전환)
	 */
	public void moveLobby() {

		// 새 스테이지 추가

		Stage stage = (Stage) loginBtn.getScene().getWindow();

		try {

			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("SelectScreen.fxml"));
			LobbyView lobbyView = new LobbyView(second);
			Menubar menubar = new Menubar(second,0);
			// 씬에 레이아웃 추가
			Scene sc = new Scene(second);

			stage.setScene(sc);

			stage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	public void moveSignUp() {

		// 새 스테이지 추가

		Stage stage = (Stage) SignUpBtn.getScene().getWindow();

		try {

			AnchorPane signUpPage = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
			
		
			SignUpController signUp= new SignUpController(signUpPage);
			// 씬에 레이아웃 추가
			Scene sc = new Scene(signUpPage);

			stage.setScene(sc);

			stage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	public void loginBeta() {
	      MultiThreadClient.sendID(MultiThreadClient.threadNum);
	      Task<Void> task = new Task<Void>() {
	         @Override
	         protected Void call() throws Exception {
	            try{
	               Platform.runLater(()->moveLobby());
	            }catch (Exception e) {
	               System.out.println("login Fail");
	            }
	            return null;
	         }
	      };
	      Thread thread = new Thread(task);
	      thread.setDaemon(true);
	      thread.start();
	   }
	public void startGame(String title) {
		introMusic.close();
	}

}