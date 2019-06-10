package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class item {

	private static Popup pop;
	
	public item(Stage stage) {
		
		pop = new Popup();
		try {
			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("Block.fxml"));
			pop.getContent().add(second);
	    	pop.setOpacity(0);
	    	pop.show(stage,551,300);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void items(int i) {
		Task<Void> task = new Task<Void>() {
			public Void call() throws Exception {
				if(i==0)
					silence();
				else if(i==1)
					blockScreen();
				else if(i==2)
					speedUp();
				return null;
			}
		};
		
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	public static void count() {
		long end,start = System.currentTimeMillis();
		while(true) {
			end = System.currentTimeMillis();
			if(end-start>3000)
				break;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void silence() {
    	Game.getMusic().silence();
    	count();
		Game.getMusic().normalVolume();
	}
	
	public static void blockScreen() {
		Platform.runLater(()->pop.setOpacity(1));
		count();
		Platform.runLater(()->pop.setOpacity(0));
	}
	
	public static void speedUp() {
		Main.NOTE_SPEED=16;
		count();
		Main.NOTE_SPEED=8;
	}
}
