package application;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;

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
				new Beat(1000, 0),
				new Beat(2000, 1),
				new Beat(3000, 2),
				new Beat(4000, 3),
				new Beat(5000, 3),
				new Beat(5500, 4),
				new Beat(5500, 5),
				new Beat(6000, 6),
				new Beat(6000, 1),
				new Beat(7000, 3),
				new Beat(7000, 4),
				new Beat(7500, 3),
				new Beat(7500, 2),
				new Beat(8000, 3),
				new Beat(8000, 4),
				new Beat(8500, 3),
				new Beat(8500, 2),
				new Beat(9000, 6),
				new Beat(9000, 1),
				new Beat(9500, 3),
				new Beat(9500, 4),
				new Beat(9500, 5),
				new Beat(10000, 3),
				new Beat(10000, 4)
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
		return null;
	}

}
