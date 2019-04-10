/**
 * 
 */
package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author 민경&태일 Pair
 *
 */
public class Menubar {

	private AnchorPane pane;
	private ImageView Background, single, multi, on, off, start, singleeffect, multieffect, oneffect, offeffect;

	public Menubar(AnchorPane pane) {
		this.pane = pane;

		Image backGroundImage = (new ImageParser("Lobby.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		Image singleImage = (new ImageParser("Lobby_single.png").getImage());
		single = new ImageView(singleImage);
		single.setLayoutX(1475);
		single.setLayoutY(545);
		single.setOnMouseClicked(e -> singleeffect());
		pane.getChildren().add(single);// 싱글 사진

		Image singleeffectImage = (new ImageParser("Lobby_singleEffect.png").getImage());
		singleeffect = new ImageView(singleeffectImage);
		singleeffect.setLayoutX(1465);
		singleeffect.setLayoutY(532);// 싱글 이펙트 사진

		Image multiImage = (new ImageParser("Lobby_multi.png").getImage());
		multi = new ImageView(multiImage);
		multi.setLayoutX(1645);
		multi.setLayoutY(545);
		multi.setOnMouseClicked(e -> multieffect());
		pane.getChildren().add(multi);// 멀티 사진

		Image multieffectImage = (new ImageParser("Lobby_multiEffect.png").getImage());
		multieffect = new ImageView(multieffectImage);
		multieffect.setLayoutX(1635);
		multieffect.setLayoutY(532);// 멀티 이펙트 사진

		Image onImage = (new ImageParser("Lobby_on.png").getImage());
		on = new ImageView(onImage);
		on.setLayoutX(1475);
		on.setLayoutY(740);
		on.setOnMouseClicked(e -> oneffect());
		pane.getChildren().add(on);// 켜기 사진

		Image oneffectImage = (new ImageParser("Lobby_onEffect.png").getImage());
		oneffect = new ImageView(oneffectImage);
		oneffect.setLayoutX(1465);
		oneffect.setLayoutY(727);// 켜기 이펙트 사진

		Image offImage = (new ImageParser("Lobby_off.png").getImage());
		off = new ImageView(offImage);
		off.setLayoutX(1645);
		off.setLayoutY(740);
		off.setOnMouseClicked(e -> offeffect());
		pane.getChildren().add(off);// 끄기 사진

		Image offeffectImage = (new ImageParser("Lobby_offEffect.png").getImage());
		offeffect = new ImageView(offeffectImage);
		offeffect.setLayoutX(1635);
		offeffect.setLayoutY(727);// 끄기 이펙트 사진

		Image startImage = (new ImageParser("Lobby_start.png").getImage());
		start = new ImageView(startImage);
		start.setLayoutX(1330);
		start.setLayoutY(870);
		pane.getChildren().add(start);// 스타트 사진

	}

	private void singleeffect() {// 싱글사진 이펙트

		pane.getChildren().add(singleeffect);
		pane.getChildren().remove(multieffect);
	}

	private void multieffect() {// 멀티사진 이펙트

		pane.getChildren().add(multieffect);
		pane.getChildren().remove(singleeffect);
	}

	private void oneffect() {// on이펙트

		pane.getChildren().add(oneffect);
		pane.getChildren().remove(offeffect);
	}

	private void offeffect() {// off이펙트

		pane.getChildren().add(offeffect);
		pane.getChildren().remove(oneffect);
	}

}