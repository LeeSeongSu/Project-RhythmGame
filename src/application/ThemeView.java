package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ThemeView {

	private AnchorPane pane;
	private ImageView Background;
	private Button btn;
	private static ArrayList<Button> buttons,selectBtns;
	private static ArrayList<String> fxmlList;
	private static ArrayList<String> screenList;
	private boolean isFirst;
	private static Label[] labels = new Label[3];
	public ThemeView(AnchorPane pane, boolean isFirst) {
		this.isFirst = isFirst;
		this.pane = pane;
		if(isFirst) {
			Image backGroundImage = (new ImageParser("Theme_bg.png").getImage());
			Background = new ImageView(backGroundImage);
			pane.getChildren().add(Background);// 로비 배경
			selectBtns = new ArrayList<Button>();
			
			for(int i=0;i<LoginSession.items.size();i++) {
				if(LoginSession.items.get(i).getItemId()==1) {
					labels[0] = new Label("보유");
					labels[0].setLayoutX(645);
					labels[0].setLayoutY(830);
					labels[0].setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:red");
					btn = new Button();
					btn.setPrefSize(481, 835);
					btn.setLayoutX(432 + (LoginSession.items.get(i).getItemId()-1) * 480);
					btn.setLayoutY(190);
					btn.setOpacity(0);
					int tmp = i;
					btn.setOnMouseClicked(e -> setItem(LoginSession.items.get(tmp).getItemId()));
					selectBtns.add(btn);
					pane.getChildren().add(btn);
					pane.getChildren().add(labels[0]);
				}else if(LoginSession.items.get(i).getItemId()==2) {
					labels[1] = new Label("보유");
					labels[1].setLayoutX(1125);
					labels[1].setLayoutY(830);
					labels[1].setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:red");
					btn = new Button();
					btn.setPrefSize(481, 835);
					btn.setLayoutX(432 + (LoginSession.items.get(i).getItemId()-1) * 480);
					btn.setLayoutY(190);
					btn.setOpacity(0);
					int tmp = i;
					btn.setOnMouseClicked(e -> setItem(LoginSession.items.get(tmp).getItemId()));
					selectBtns.add(btn);
					pane.getChildren().add(btn);
					pane.getChildren().add(labels[1]);
				}else if(LoginSession.items.get(i).getItemId()==3) {
					labels[2] = new Label("보유");
					labels[2].setLayoutX(1605);
					labels[2].setLayoutY(830);
					labels[2].setStyle("-fx-font-size : 30px; -fx-font-weight : bold; -fx-text-fill:red");
					btn = new Button();
					btn.setPrefSize(481, 835);
					btn.setLayoutX(432 + (LoginSession.items.get(i).getItemId()-1) * 480);
					btn.setLayoutY(190);
					btn.setOpacity(0);
					int tmp = i;
					btn.setOnMouseClicked(e -> setItem(LoginSession.items.get(tmp).getItemId()));
					selectBtns.add(btn);
					pane.getChildren().add(btn);
					pane.getChildren().add(labels[2]);
				}
			}
		}
		buttons = new ArrayList<Button>();

		for (int a = 0; a < 2; a++) {
			btn = new Button();
			btn.setPrefSize(287, 87);

			btn.setLayoutX(100);
			btn.setLayoutY(463 + a * 160);
			btn.setOpacity(0);
			buttons.add(btn);

			pane.getChildren().add(btn);
			
		}
		
		

		
		buttons.get(0).setOnMouseClicked(e -> btnClick1(0));
		buttons.get(1).setOnMouseClicked(e -> btnClick1(1));

		
		fxmlList = new ArrayList<String>();
		
		fxmlList.add("ThemeGameScreen.fxml");
		fxmlList.add("ThemeNoteScreen.fxml");

		screenList = new ArrayList<String>();
	
		screenList.add("ThemeGameView");
		screenList.add("ThemeNoteView");
	}
	
	public void setItem(int itemId) {
		int isSelect = JOptionPane.showConfirmDialog(null, "아이템을 설정하시겠습니까?", "확인", JOptionPane.OK_CANCEL_OPTION);
		if(isSelect==0) {
			LoginSession.chooseEffect = String.valueOf(itemId);
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

	private void btnClick1(int i) {

		Stage stage = (Stage) buttons.get(i).getScene().getWindow();

		try {
			if (fxmlList.get(i) == "ThemeGameScreen.fxml") {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("ThemeGameScreen.fxml"));
				new ThemeGameView(nextScreen);
				new Menubar(nextScreen, 1);
				new ThemeView(nextScreen, false);
				Scene sc = new Scene(nextScreen);
				stage.setScene(sc);

				stage.show();
			} else {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("ThemeNoteScreen.fxml"));
				new ThemeNoteView(nextScreen);
				new Menubar(nextScreen, 1);
				new ThemeView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
