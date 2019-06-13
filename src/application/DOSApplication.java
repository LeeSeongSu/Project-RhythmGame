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
 * @author 팀원 전체 해당 클래스는 메인디능을 담당하는 JavaFX Application클래스를 상속받는 클래스입니다.
 */
public class DOSApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(DOSApplication.class.getResource("MainScreen.fxml"));
			Font f = Font.loadFont(Class.forName("application.Main").getResourceAsStream("/images/210 옴니고딕020.ttf"), 50);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(DOSApplication.class.getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					MultiThreadClient.roomExit();
					MultiThreadClient.exit();
					System.out.println("Exit");
					System.exit(0);
				}
			});
			
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
