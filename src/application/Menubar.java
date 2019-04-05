/**
 * 
 */
package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author 민경&태일 Pair
 *
 */
public class Menubar {
	private AnchorPane pane;
	private ImageView menuBackground, backIcon, shopIcon, settingIcon;

	public Menubar(AnchorPane pane) {
		this.pane = pane;
		Image menuBackgroundImage = (new ImageParser("menu_background.png").getImage());
		menuBackground = new ImageView(menuBackgroundImage);
		menuBackground.setLayoutX(0);
		menuBackground.setLayoutY(0);
		pane.getChildren().add(menuBackground);
		
		Image backIconImage = (new ImageParser("back_icon.png").getImage());
		backIcon = new ImageView(backIconImage);
		backIcon.setLayoutX(0);
		backIcon.setLayoutY(0);
		backIcon.setOnMouseClicked(e-> System.exit(0));
		pane.getChildren().add(backIcon);
		
		Image shopkIconImage = (new ImageParser("shop_icon.png").getImage());
		shopIcon = new ImageView(shopkIconImage);
		shopIcon.setLayoutX(1800);
		shopIcon.setLayoutY(0);
		pane.getChildren().add(shopIcon);
		
		Image settingIconImage = (new ImageParser("setting_icon.png").getImage());
		settingIcon = new ImageView(settingIconImage);
		settingIcon.setLayoutX(1680);
		settingIcon.setLayoutY(12);
		pane.getChildren().add(settingIcon);
	}

	
}
