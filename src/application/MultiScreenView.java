package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MultiScreenView {
	private AnchorPane pane;
	private Button startBtn;
	private static Button exButton;
	private static ArrayList<Button> buttons;
	private int musicIndex;
	private static boolean start=false;
	private ArrayList<String> room;
	private ArrayList<String> ready;
	private	String text;
	

	public MultiScreenView(AnchorPane pane) {
		this.pane = pane;
		
		start=false;
		
		startBtn = new Button("Start");
		startBtn.setPrefSize(164, 92);
		startBtn.setLayoutX(20);
		startBtn.setLayoutY(20);
		startBtn.setOnMouseClicked(e -> clickStartBtn());
		
		buttons=new ArrayList<Button>();
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());
		buttons.add(new Button());
		
		
		for(int i=0; i<buttons.size();i++) {
			exButton=buttons.get(i);
			exButton.setLayoutX(500+250*i);
			exButton.setLayoutY(500);
			exButton.setPrefSize(200, 200);
			pane.getChildren().add(exButton);
		}
		
		Thread thread = new Thread() { // fx UI를 변경하는 쓰레드에 접근하기 위해 만든 쓰레드
            @Override
            public void run() {
            	
                while (!start) {
                    Platform.runLater(() -> {

                    	room= MultiThreadClient.getRoom();
                    	ready=MultiThreadClient.getReady();
                		for(int i=0; i<room.size();i++) {
                			exButton=buttons.get(i);
                			text=room.get(i);
                			if(ready.get(i).equals("1")) {
                				text+="\nReady";
                			}
                			exButton.setText(text);
                		}
                		
                		for(int i= room.size(); i<buttons.size();i++) {
                			buttons.get(i).setText("");
                		}
                    });
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
                Platform.runLater(() -> GameScreen());
            }
        };
        thread.setName("roomUpdateThread");
        thread.setDaemon(true);
        thread.start();
		pane.getChildren().add(startBtn);
		
	}
	
	public void clickStartBtn() {
		if(!room.get(0).equals(MultiThreadClient.clientId)) {
			ready();
		}
		else {
			
			for(int i=1; i<ready.size(); i++) {
				if(ready.get(i).equals("0")) {
					System.out.println("모든 플레이어가 준비를 하지 않았습니다.");
					return;
				}
			}
			
			MultiThreadClient.start();

		}
	}

	public void GameScreen() {
		Stage stage = (Stage) startBtn.getScene().getWindow();
	
		try {
			AnchorPane pane = FXMLLoader.load(Class.forName("application.Main").getResource("GameScreen.fxml"));

			// 씬에 레이아웃 추가
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			Game game = new Game(SongView.mp3List.get(musicIndex), pane, sc);
			stage.show();
			Task<Void> task = new Task<Void>() {
				public Void call() throws Exception {
					game.run();
					return null;
				}
			};
			Thread t = new Thread(task);
			t.setDaemon(true);
			t.run();
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	public void ready() {
		MultiThreadClient.ready();
	}
	public static void setStart(boolean bool) {
		start=bool;
	}
}