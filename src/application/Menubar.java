/**
 * 
 */
package application;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

/**
 * @author  재원(코딩), 성수(로직) 페어 :홈,테마,스토어 버튼 이벤트 추가
 *
 */
public class Menubar {

   private AnchorPane pane;
   private ImageView Background, select;
   private BackgroundImage selectBtnBgImg;
   private Button homeBtn, themeBtn, storeBtn;

   public Menubar(AnchorPane pane) {
      this.pane = pane;

      Image selectImage = (new ImageParser("Lobby_selectEffect.png").getImage());

      selectBtnBgImg = new BackgroundImage(selectImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER, null);

      homeBtn = new Button();
      homeBtn.setPrefSize(388, 125);
      homeBtn.setBackground(new Background(selectBtnBgImg));
      homeBtn.setLayoutX(0);
      homeBtn.setLayoutY(0);
      homeBtn.setOpacity(1);
      homeBtn.setOnMouseClicked(e -> homeBtnClick());

      themeBtn = new Button();
      themeBtn.setPrefSize(388, 125);
      themeBtn.setBackground(new Background(selectBtnBgImg));
      themeBtn.setLayoutX(381);
      themeBtn.setLayoutY(0);
      themeBtn.setOpacity(0);
      themeBtn.setOnMouseClicked(e -> themeBtnClick());

      storeBtn = new Button();
      storeBtn.setPrefSize(388, 125);
      storeBtn.setBackground(new Background(selectBtnBgImg));
      storeBtn.setLayoutX(760);
      storeBtn.setLayoutY(0);
      storeBtn.setOpacity(0);
      storeBtn.setOnMouseClicked(e -> storeBtnClick());

      pane.getChildren().add(homeBtn);
      pane.getChildren().add(themeBtn);
      pane.getChildren().add(storeBtn);

   }

   private void homeBtnClick() {// off

      homeBtn.setBackground(new Background(selectBtnBgImg));
      homeBtn.setOpacity(1);
      themeBtn.setOpacity(0);
      storeBtn.setOpacity(0);
   }

   private void themeBtnClick() {// off

      themeBtn.setBackground(new Background(selectBtnBgImg));
      homeBtn.setOpacity(0);
      themeBtn.setOpacity(1);
      storeBtn.setOpacity(0);

   }

   private void storeBtnClick() {

      storeBtn.setBackground(new Background(selectBtnBgImg));
      homeBtn.setOpacity(0);
      themeBtn.setOpacity(0);
      storeBtn.setOpacity(1);

   }

}