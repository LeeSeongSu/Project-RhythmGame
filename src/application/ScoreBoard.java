package application;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ScoreBoard {
	ArrayList<String> room;
	ArrayList<String> scores;
	boolean playing;
	private ArrayList<Label> textList1 = new ArrayList<Label>();
	private ArrayList<Label> textList2 = new ArrayList<Label>();
	private Label scoreLbl;
	
	public ScoreBoard(Pane pane) {
		room=MultiThreadClient.getRoom();
		scores=MultiThreadClient.getScore();
		
		Label label;
		
		Image scoreBoardImage = new ImageParser("Ranking.png").getImage();
		ImageView scoreBoard = new ImageView(scoreBoardImage);
		scoreBoard.setLayoutX(70);
		scoreBoard.setLayoutY(200);
		pane.getChildren().add(scoreBoard);
		
		playing=true;
		
		for(int i=0; i<room.size(); i++) {
			scores.add("0");
			label = new Label(room.get(i));
			label.setLayoutX(170);
			label.setLayoutY(374+58*i);
			pane.getChildren().add(label);
			
			textList1.add(label);
			
			label = new Label(scores.get(i));
			label.setLayoutX(330);
			label.setLayoutY(374+58*i);
			pane.getChildren().add(label);
			label.setStyle("-fx-text-fill: white; -fx-font-size : 30px;");
			textList2.add(label);
		}
		
		scoreLbl = new Label("");
		scoreLbl.setLayoutX(380);
		scoreLbl.setLayoutY(720);
		pane.getChildren().add(scoreLbl);
		scoreLbl.setStyle("-fx-text-fill: white; -fx-font-size : 30px;");
		
		Thread thread = new Thread() { // fx UI를 변경하는 쓰레드에 접근하기 위해 만든 쓰레드
            @Override
            public void run() {
            	
                while (playing) {
                    Platform.runLater(() -> {

                    	room= MultiThreadClient.getRoom();
                    	scores=MultiThreadClient.getScore();
                		for(int i=0; i<room.size();i++) {
                			textList1.get(i).setText(room.get(i));
                			textList2.get(i).setText(scores.get(i));
                			
                			if(room.get(i).equals(MultiThreadClient.clientId)) {
                				textList1.get(i).setStyle("-fx-text-fill: red; -fx-font-size : 30px;");
                				scoreLbl.setText(""+i+1);
                			}else
                				textList1.get(i).setStyle("-fx-text-fill: white; -fx-font-size : 30px;");
                	
                		}
                		for(int i=room.size(); i<textList1.size();i++) {
                			textList1.get(i).setText("Exit");
                			textList2.get(i).setText("");
                			textList1.get(i).setStyle("-fx-text-fill: white; -fx-font-size : 30px;");
                		}
                			
                    });
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
            }
        };
        thread.setName("gameScoreUpdate");
        thread.setDaemon(true);
        thread.start();
	}

}
