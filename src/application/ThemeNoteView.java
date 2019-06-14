package application;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ThemeNoteView {
	private AnchorPane pane;
	private Button btn;
	private ImageView Background;
	private static Label[] labels = new Label[3];
	public ThemeNoteView(AnchorPane pane) {
		this.pane = pane;
		
		Image backGroundImage = (new ImageParser("Theme_Note_bg.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경
		
		for(int i=0;i<LoginSession.items.size();i++) {
			if(LoginSession.items.get(i).getItemId()==4) {
				labels[0] = new Label("보유");
				labels[0].setLayoutX(645);
				labels[0].setLayoutY(700);
				labels[0].setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:red");
				btn = new Button();
				btn.setPrefSize(481, 835);
				btn.setLayoutX(432 + (LoginSession.items.get(i).getItemId()-4) * 480);
				btn.setLayoutY(190);
				btn.setOpacity(0);
				int tmp = i;
				btn.setOnMouseClicked(e -> setItem(LoginSession.items.get(tmp).getItemId()));
				pane.getChildren().add(btn);
				pane.getChildren().add(labels[0]);
			}else if(LoginSession.items.get(i).getItemId()==5) {
				labels[1] = new Label("보유");
				labels[1].setLayoutX(1125);
				labels[1].setLayoutY(700);
				labels[1].setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:red");
				btn = new Button();
				btn.setPrefSize(481, 835);
				btn.setLayoutX(432 + (LoginSession.items.get(i).getItemId()-4) * 480);
				btn.setLayoutY(190);
				btn.setOpacity(0);
				int tmp = i;
				btn.setOnMouseClicked(e -> setItem(LoginSession.items.get(tmp).getItemId()));
				pane.getChildren().add(btn);
				pane.getChildren().add(labels[1]);
			}else if(LoginSession.items.get(i).getItemId()==6) {
				labels[2] = new Label("보유");
				labels[2].setLayoutX(1605);
				labels[2].setLayoutY(700);
				labels[2].setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:red");
				btn = new Button();
				btn.setPrefSize(481, 835);
				btn.setLayoutX(432 + (LoginSession.items.get(i).getItemId()-4) * 480);
				btn.setLayoutY(190);
				btn.setOpacity(0);
				int tmp = i;
				btn.setOnMouseClicked(e -> setItem(LoginSession.items.get(tmp).getItemId()));
				pane.getChildren().add(btn);
				pane.getChildren().add(labels[2]);
			}
		}
	}
	public void setItem(int itemId) {
		int isSelect = JOptionPane.showConfirmDialog(null, "아이템을 설정하시겠습니까?", "확인", JOptionPane.OK_CANCEL_OPTION);
		if(isSelect==0) {
			LoginSession.chooseNote = String.valueOf(itemId);
			Map<String, String> map = new HashMap<>();
			map.put("memberId", LoginSession.memberId);
			map.put("chooseEffect", LoginSession.chooseEffect);
			map.put("chooseNote", LoginSession.chooseNote);
			HttpConnector hc = new HttpConnector("setItem", map);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						String result = hc.request();
						if (result.equals("Error")) {
							JOptionPane.showMessageDialog(null, "아이템 설정 오류입니다.");
						} else {
							JOptionPane.showMessageDialog(null, "아이템 설정 성공입니다.");
						}
					} catch (Exception e) {
						System.out.println("Setting Fail");
					}
					return null;
				}
			};
			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();
		}
	}

}
