package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Item {

	private static Popup pop;
	static int sIndex=1,bIndex,speedIndex;
	static boolean silence,block,speed;
	
	public Item(Stage stage) {
		silence = false;
		block=false;
		speed = false;
		sIndex=1;
		bIndex=1;
		speedIndex=1;
		pop = new Popup();
		try {
			AnchorPane second = FXMLLoader.load(Class.forName("application.Main").getResource("/view/Block.fxml"));
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
				if(i==0) {
					if(silence)
						sIndex++;
					else {
						silence=true;
						silence();
					}
				}
				else if(i==1) {
					if(block)
						bIndex++;
					else {
						block=true;
						blockScreen();
					}
				}
				else if(i==2) {
					if(speed)
						speedIndex++;
					else {
						speed=true;
						speedUp();
				
					}
				}
				return null;
			}
		};
		
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}
	
	public static void count(int i) {
		long end,start = System.currentTimeMillis();
		int index = 0;
		while(true) {
			end = System.currentTimeMillis();
			if(i==0)
				index=sIndex;
			else if(i==1)
				index=bIndex;
			else if(i==2)
				index=speedIndex;
			
			if(end-start>3000*index)
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
    	DOSApplicationController.introMusic.normalVolume();
    	count(0);
    	sIndex=1;
		silence=false;
		DOSApplicationController.introMusic.silence();
		Game.getMusic().normalVolume();
		
  
	}
	
	public static void blockScreen() {
		Platform.runLater(()->pop.setOpacity(1));
		count(1);
		bIndex=1;
		block=false;
		Platform.runLater(()->pop.setOpacity(0));
	}
	
	public static void speedUp() {
		DOSApplication.NOTE_SPEED=16;
		count(2);
		speedIndex=1;
		speed=false;
		DOSApplication.NOTE_SPEED=8;
	}
}
