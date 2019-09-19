package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StoreGameView {
	private AnchorPane pane;
	private ImageView Background;
	private Button btn;
	private static ArrayList<Button> buttons;

	public StoreGameView(AnchorPane pane) {
		// TODO Auto-generated constructor stub
		this.pane = pane;
		Image backGroundImage = (new ImageParser("Store_Game.png").getImage());
		Background = new ImageView(backGroundImage);
		pane.getChildren().add(Background);// 로비 배경

		buttons = new ArrayList<Button>();
		for (int a = 0; a < 3; a++) {
			btn = new Button();
			btn.setPrefSize(481, 835);

			btn.setLayoutX(432 + a * 480);
			btn.setLayoutY(190);
			btn.setOpacity(0);
			int tmp = a;
			btn.setOnMouseClicked(e -> buyItem(tmp + 1));
			buttons.add(btn);

			pane.getChildren().add(btn);
		}
		Menubar menubar = new Menubar(pane, 2);
	}

	public void buyItem(int itemId) {
		int isBuy = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?", "확인", JOptionPane.OK_CANCEL_OPTION);
		for (int i = 0; i < LoginSession.items.size(); i++) {
			if (LoginSession.items.get(i).getItemId() == itemId) {
				JOptionPane.showMessageDialog(null, "이미 구매한 아이템입니다.");
				return;
			}
		}
		if (isBuy == 0) {
			try {
				if (Integer.parseInt(LoginSession.money) < 3000) {
					JOptionPane.showMessageDialog(null, "돈이 부족합니다.");
					return;
				}
				LoginSession.money =String.valueOf(Integer.parseInt(LoginSession.money)-3000);
				Map<String, String> map = new HashMap<>();
				map.put("memberId", LoginSession.memberId);
				map.put("money", "3000");
				map.put("itemId", String.valueOf(itemId));
				HttpConnector hc = new HttpConnector("buyItem", map);
				Task<Void> task = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						try {
							String result = hc.request();
							if (result.equals("Error")) {
								JOptionPane.showMessageDialog(null, "아이템을 구매할 수 없습니다.");
							} else {
								JOptionPane.showMessageDialog(null, "아이템 구매에 성공하셨습니다.");
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "인터넷 연결을 확인해주세요.");
						}
						return null;
					}
				};
				Thread thread = new Thread(task);
				thread.setDaemon(true);
				thread.start();
				Map<String, String> mapItem = new HashMap<>();
				mapItem.put("memberId", LoginSession.memberId);
				HttpConnector hcc = new HttpConnector("loadItems", mapItem);
				Task<Void> taskk = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						try{
							List<String> requestList = new ArrayList<>();
							requestList.add("itemId");
							requestList.add("name");
							requestList.add("image");
							LoginSession.items = hcc.request(requestList,true);
						}catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				};
				Thread threadd = new Thread(taskk);
				threadd.setDaemon(true);
				threadd.start();
				
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("/view/StoreGameScreen.fxml"));
				new StoreGameView(nextScreen);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);
				Stage stage = (Stage) pane.getScene().getWindow();
				stage.setScene(sc);
				stage.show();
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
