package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author 팀원 전체 해당 클래스는 메인기능을 담당하는 JavaFX Application클래스를 상속받는 클래스입니다.
 */
public class DOSApplication extends Application {
	
	public static int SCREEN_WIDTH = 1920;
	public static int SCREEN_HEIGHT = 1080;
	public static int NOTE_SPEED = 8;
	public static int SLEEP_TIME = 10;
	public static int REACH_TIME = 1;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(DOSApplication.class.getResource("/view/MainScreen.fxml"));
			Font f = Font.loadFont(Class.forName("application.DOSApplication").getResourceAsStream("/images/210 옴니고딕020.ttf"), 50);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(DOSApplication.class.getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					MultiThreadClient.roomExit();
					MultiThreadClient.exit();
					System.exit(0);
				}
			});
			
			primaryStage.show();
			Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	                System.out.println("JVM은 종료중...");
	            }
	        });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
