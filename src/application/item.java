package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class item {
	
	private static Stage stage;
	private static Popup pop;
	
	public item(Stage stage) {
		this.stage=stage;
		
		pop = new Popup();
		try {
			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("Block.fxml"));
			pop.getContent().add(second);
	    	pop.setOpacity(0);
	    	pop.show(stage,551,289);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void silence() {
		Task<Void> task = new Task<Void>() {
		    public Void call() throws Exception {
		    	Game.getMusic().silence();
		    	long end,start = System.currentTimeMillis();

				while(true) {
					end = System.currentTimeMillis();
					if(end-start>3000)
						break;
					Thread.sleep(100);
				}
				
				Game.getMusic().normalVolume();
		        return null;
		    }
		};
		 
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	public static void blockScreen() {
		Task<Void> task = new Task<Void>() {
		    public Void call() throws Exception {
		    	Platform.runLater(()->pop.setOpacity(1));
		    	long end,start = System.currentTimeMillis();
				while(true) {
					end = System.currentTimeMillis();
					if(end-start>3000)
						break;
					Thread.sleep(100);
				}
				
				Platform.runLater(()->pop.setOpacity(0));
		        return null;
		    }
		};
		 
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
}
