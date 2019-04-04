package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author �쟾遺� MainScreen.fxml�쓽 而⑦듃濡ㅻ윭�뙆�씪
 */
public class DOSApplicationController implements Initializable {
   @FXML
   private ImageView backgroundImageView;
   @FXML
   private Button btnlogin;

   @Override
   public void initialize(URL url, ResourceBundle rb) {
      Image backgroundImage = (new ImageParser("main.gif").getImage());
      backgroundImageView.setImage(backgroundImage);
      Music introMusic = new Music("Game On.mp3", true);
      try {
         Thread.sleep(4500);
         introMusic.start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /**
    * @author 재원,성수 페어(페이지 전환)
    */
   public void movePage() {

      // 새 스테이지 추가

      Stage newStage = new Stage();

      Stage stage = (Stage) btnlogin.getScene().getWindow();

      try {

         Parent second = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

         // 씬에 레이아웃 추가

         Scene sc = new Scene(second);

         stage.setScene(sc);
   
         stage.show();

      } catch (IOException e) {

         e.printStackTrace();

      }

   }

}