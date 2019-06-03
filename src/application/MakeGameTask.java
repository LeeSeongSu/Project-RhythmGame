package application;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MakeGameTask extends Task<Void>{
	Beat[] beats;
	Note[] notes;
	Music music;
	AnchorPane pane;
	ArrayList<Note> noteList;
	public MakeGameTask(Music music,AnchorPane pane,ArrayList<Note> noteList) {
		this.music = music;
		this.pane = pane;
		this.noteList = noteList;
	}
	
	@Override
	protected Void call() throws Exception {
	
		beats = new Beat[] {
				//느린 부분
				new Beat(1850, 0),
				new Beat(2720, 2),
				new Beat(3580, 4),
				new Beat(4450, 5),
				new Beat(5260, 3),
				new Beat(6150, 6),
				new Beat(6950, 4),
				new Beat(7820, 3),
				new Beat(8640, 1),
				new Beat(9530, 2),
				new Beat(10400, 5),
				new Beat(11270, 4),
				new Beat(12140, 3),
				new Beat(12950, 6),
				new Beat(13420, 3),
				new Beat(13820, 2),
				new Beat(14310, 5),
				new Beat(14710, 3),
				new Beat(15110, 1),
				new Beat(15510, 5),
				//빠른부분
				new Beat(16000, 0),
				new Beat(16250, 3),
				new Beat(16500, 0),
				new Beat(16750, 3),
				new Beat(17000, 4),
				new Beat(17250, 6),
				new Beat(17500, 5),
				new Beat(17750, 1),				
				new Beat(18000, 3),
				new Beat(18250, 0),
				new Beat(18500, 0),
				new Beat(18750, 3),
				new Beat(19000, 3),
				new Beat(19250, 6),
				new Beat(19500, 5),
				new Beat(19750, 1),
				new Beat(20000, 3),		
				new Beat(20250, 0),
				new Beat(20500, 0),
				new Beat(20750, 3),
				new Beat(21000, 3),
				new Beat(21250, 6),
				new Beat(21500, 5),
				new Beat(21750, 1),
				new Beat(22000, 3),			
				new Beat(22250, 0),
				new Beat(22500, 6),
				new Beat(22750, 3),
				new Beat(23000, 3),
				new Beat(23250, 6),
				new Beat(23500, 5),
				new Beat(23750, 1),
				new Beat(24000, 3),			
				new Beat(24250, 0),
				new Beat(24500, 4),
				new Beat(24750, 3),
				new Beat(25000, 3),
				new Beat(25250, 6),
				new Beat(25500, 5),
				new Beat(25750, 1),
				new Beat(26000, 3),		
				new Beat(26250, 0),
				new Beat(26500, 1),
				new Beat(26750, 3),
				new Beat(27000, 2),
				new Beat(27250, 6),
				new Beat(27500, 5),
				new Beat(27750, 1),
				new Beat(28000, 3),	
				new Beat(28250, 6),
				new Beat(28500, 5),
				new Beat(28750, 1),
				new Beat(29000, 3),					
				new Beat(29250, 1),
				new Beat(29250, 2),
				new Beat(29250, 4),
				new Beat(29250, 5),			
				new Beat(29500, 1),
				new Beat(29750, 3),				
				new Beat(29950, 3),					
				new Beat(30250, 6),
				new Beat(30350, 5),
				new Beat(30450, 1),				
				new Beat(30700, 6),
				new Beat(30950, 5),
				new Beat(31150, 1),			
				new Beat(31400, 6),
				new Beat(31950, 5),	
				new Beat(32100, 3),
				new Beat(32300, 4),				
				new Beat(32550, 3),		
				new Beat(32800, 0),
				new Beat(33050, 1),
				new Beat(33300, 3),
				new Beat(33750, 3),					
				new Beat(34000, 3),		
				new Beat(34250, 0),
				new Beat(34500, 1),
				new Beat(34750, 3),
				new Beat(35000, 3),				
				new Beat(35250, 3),		
				new Beat(35500, 0),
				new Beat(35750, 1),
				new Beat(36000, 3),
				new Beat(36250, 3),	
				new Beat(36500, 3),		
				new Beat(36750, 0),
				new Beat(37000, 1),
				new Beat(37250, 5),
				new Beat(37500, 6),
				new Beat(37750, 3),
				new Beat(38000, 1),
				new Beat(38250, 2),
				new Beat(38500, 3),	
				new Beat(38750, 0),
				new Beat(39000, 1),
				new Beat(39250, 5),
				new Beat(39500, 3),	
				new Beat(39750, 0),
				new Beat(40000, 1),
				new Beat(40250, 5),
				new Beat(40500, 3),	
				new Beat(40750, 0),
				new Beat(41000, 1),
				new Beat(41250, 3),
				new Beat(41500, 2),	
				new Beat(41750, 5),
				new Beat(42000, 1),
				new Beat(42250, 4),
				new Beat(42500, 3),	
				new Beat(42750, 0),
				new Beat(43000, 1),
				new Beat(43250, 4),
				new Beat(43500, 5),	
				new Beat(43750, 0),
				new Beat(44000, 1),
				new Beat(44250, 2),
				new Beat(44500, 3),	
				new Beat(44750, 0),
				new Beat(45000, 1),
				new Beat(45250, 3),
				new Beat(45500, 4),	
				new Beat(45750, 5),
				new Beat(46000, 1),
				new Beat(46250, 3),
				new Beat(46500, 5),	
				new Beat(46750, 0),
				new Beat(47000, 1),
				new Beat(47250, 6),
				new Beat(47500, 3),	
				new Beat(47750, 0),
				new Beat(48000, 5),
				new Beat(48250, 2),
				new Beat(48500, 3),	
				new Beat(48750, 0),
				new Beat(49000, 1),
				new Beat(49250, 3),
				new Beat(49500, 4),	
				new Beat(49750, 0),
				new Beat(50000, 1),
				new Beat(50250, 6),
				new Beat(50500, 3),	
				new Beat(50750, 2),
				new Beat(51000, 1),
				new Beat(51250, 3),
				new Beat(51500, 2),	
				new Beat(51750, 5),
				new Beat(52000, 2),
				new Beat(52250, 4),
				new Beat(52500, 3),	
				new Beat(52750, 2),
				new Beat(53000, 1),
				new Beat(53250, 2),
				new Beat(53500, 1),	
				new Beat(53750, 6),
				new Beat(54000, 1),
				new Beat(54250, 3),
				new Beat(54500, 6),	
				new Beat(54750, 0),
				new Beat(55000, 6),
				new Beat(55250, 3),
				new Beat(55500, 5),	
				new Beat(55750, 6),
				new Beat(56000, 1),
				new Beat(56250, 3),
				new Beat(56500, 5),	
				new Beat(56750, 6),
				new Beat(57000, 1),
				new Beat(57250, 3),
				new Beat(57500, 3),	
				new Beat(57750, 5),
				new Beat(58000, 1),
				new Beat(58250, 3),
				new Beat(58500, 2),	
				new Beat(58750, 0),
				new Beat(59000, 1),
				new Beat(59250, 3),
				new Beat(59500, 5),	
				new Beat(59750, 0),
				new Beat(60000, 1),
				new Beat(60250, 3),
				new Beat(60500, 5),	
				new Beat(60750, 0),
				new Beat(61000, 1),
				new Beat(61250, 3),
				new Beat(61500, 0),	
				new Beat(61750, 5),
				new Beat(62000, 1),
				new Beat(62250, 3),
				new Beat(62500, 3),	
				new Beat(62750, 0),
				new Beat(63000, 1),
				new Beat(63250, 3),
				new Beat(63500, 3),	
				new Beat(63750, 5),
				new Beat(64000, 1),
				new Beat(64250, 0),
				new Beat(64500, 3),	
				new Beat(64750, 2),
				new Beat(65000, 1),
				new Beat(65250, 4),
				new Beat(65500, 3),	
				new Beat(65750, 4),
				new Beat(66000, 5),
				new Beat(66250, 3),
				new Beat(66500, 3),	
				new Beat(66750, 2),
				new Beat(67000, 1),
				new Beat(67250, 3),
				new Beat(67500, 3),	
				new Beat(67750, 2),
				new Beat(68000, 4),
				new Beat(68250, 3),
				new Beat(68500, 3),	
				new Beat(68750, 0),
				new Beat(69000, 5),
				new Beat(69250, 3),
				new Beat(69500, 6),	
				new Beat(69750, 0),
				new Beat(70000, 1),
				new Beat(70250, 2),
				new Beat(70450, 3),	
				new Beat(70450, 2),	
				new Beat(70450, 1),	
				new Beat(70450, 4),	
				new Beat(70450, 5),	
				new Beat(70450, 6),	
						
		};
		Platform.runLater(()-> pane.getChildren().add(ImageStorage.judgeImgView));
		Note[] notes = new Note[beats.length];
		for(int i=0; i<beats.length;i++) {
			notes[i]= new Note(beats[i].getNoteLocation(), pane);
			noteList.add(notes[i]);
		}
		int i=0;
		music.changeFlag();
		music.start();
		System.out.println(notes.length);
		for(i=0;i<notes.length;i++) {
			Thread.sleep(1);
			if(beats[i].getTime()<=music.getTime()) {
				Note tmp = notes[i];
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						tmp.screenDraw();
						tmp.setDaemon(true);
						tmp.start();
					}
				});
			}else {
				i--;
			}
		}

		TimeUnit.SECONDS.sleep(8);// 마지막노트 떨어지는 시점에서 딜레이 8초

		Platform.runLater(() -> {

			Stage stage = (Stage) pane.getScene().getWindow(); 

			try {

				AnchorPane score = FXMLLoader.load(Class.forName("application.Main").getResource("ScoreScreen.fxml"));

				ScoreView Score = new ScoreView(score);
				
				Scene sc = new Scene(score);

				stage.setScene(sc);

				stage.show();

			} catch (Exception e) {

				e.printStackTrace();

			}
		});
		return null;
	}

}
