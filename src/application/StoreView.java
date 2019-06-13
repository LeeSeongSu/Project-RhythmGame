package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StoreView {

	private AnchorPane pane;
	private ImageView Background;
	private Button btn1;
	private static ArrayList<Button> buttons1;
	private static ArrayList<String> fxmlList1;
	private static ArrayList<String> screenList1;
	private static ArrayList<Button> buttons;
	private boolean isFirst;
	private int[] moneys = {100,400,700,1500,5000,13000};
	public StoreView(AnchorPane pane, boolean isFirst) {
		this.isFirst = isFirst;
		this.pane = pane;
		if(isFirst) {
			Image backGroundImage = (new ImageParser("Store_IGM.png").getImage());
			Background = new ImageView(backGroundImage);
			pane.getChildren().add(Background);// 로비 배경
			for (int a = 0; a < 6; a++) {
				Button btn = new Button();
				if(a<3) {
					btn.setPrefSize(472, 372);
					btn.setLayoutX(443 + a * 470);
					btn.setLayoutY(215);
					btn.setOpacity(0.5);
				}else {
					btn.setPrefSize(472, 372);
					btn.setLayoutX(443 + (a-3) * 470);
					btn.setLayoutY(590);
					btn.setOpacity(0.5);
				}
				int money = moneys[a];
				btn.setOnMouseClicked(e-> butIGM(money));
				pane.getChildren().add(btn);

			}
		}
		buttons1 = new ArrayList<Button>();

		for (int a = 0; a < 3; a++) {
			btn1 = new Button();
			btn1.setPrefSize(287, 87);

			btn1.setLayoutX(96);
			btn1.setLayoutY(398 + a * 160);
			btn1.setOpacity(0);
			buttons1.add(btn1);

			pane.getChildren().add(btn1);
			
		}
		
		buttons = new ArrayList<Button>();

		buttons1.get(0).setOnMouseClicked(e -> btnClick1(0));
		buttons1.get(1).setOnMouseClicked(e -> btnClick1(1));
		buttons1.get(2).setOnMouseClicked(e -> btnClick1(2));

		
		fxmlList1 = new ArrayList<String>();
		fxmlList1.add("StoreIgmScreen.fxml");
		fxmlList1.add("StoreGameScreen.fxml");
		fxmlList1.add("StoreNoteScreen.fxml");

		screenList1 = new ArrayList<String>();
		screenList1.add("StoreIgmView");
		screenList1.add("StoreGameView");
		screenList1.add("StoreNoteView");
	}
	
	
	public void butIGM(int money) {
		int isBuy = JOptionPane.showConfirmDialog(null, "정말 구매하시겠습니까?","확인",JOptionPane.OK_CANCEL_OPTION);
		if(isBuy==0) {
			LoginSession.money= String.valueOf(Integer.parseInt(LoginSession.money)+money);
			AnchorPane nextScreen;
			try {
				Map<String, String> map = new HashMap<>();
				map.put("memberId", LoginSession.memberId);
				map.put("money", LoginSession.money);
				HttpConnector hc = new HttpConnector("buyIGM", map);
				Task<Void> task = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						try {
							String result = hc.request();
							if (result.equals("Error")) {
								JOptionPane.showMessageDialog(null, "구매에 실패하셨습니다.");
							} else {
								JOptionPane.showMessageDialog(null, "구매에 성공하셨습니다.");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				};
				Thread thread = new Thread(task);
				thread.setDaemon(true);
				thread.start();
				nextScreen = FXMLLoader.load(getClass().getResource("StoreIgmScreen.fxml"));
				new StoreIgmView(nextScreen);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);
				Stage stage = (Stage) pane.getScene().getWindow();
				stage.setScene(sc);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void btnClick1(int i) {

		Stage stage = (Stage) buttons1.get(i).getScene().getWindow();

		try {

			if (fxmlList1.get(i) == "StoreIgmScreen.fxml") {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("StoreIgmScreen.fxml"));
				new StoreIgmView(nextScreen);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			} else if (fxmlList1.get(i) == "StoreGameScreen.fxml") {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("StoreGameScreen.fxml"));
				new StoreGameView(nextScreen);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			} else {
				AnchorPane nextScreen = FXMLLoader.load(getClass().getResource("StoreNoteScreen.fxml"));
				new StoreNoteView(nextScreen);
				new StoreView(nextScreen, false);
				Scene sc = new Scene(nextScreen);

				stage.setScene(sc);

				stage.show();
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
