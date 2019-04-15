package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;

public class MakeGameTask extends Task<Void>{
	Beat[] beats;
	Note[] notes;
	Music music;
	AnchorPane pane;
	
	public MakeGameTask(Music music,AnchorPane pane) {
		this.music = music;
		this.pane = pane;
	}
	
	@Override
	protected Void call() throws Exception {
		beats = new Beat[] {
				new Beat(1000, 0),
				new Beat(2000, 1),
				new Beat(3000, 2),
				new Beat(4000, 3)
		};
		Note[] notes = new Note[beats.length];
		for(int i=0; i<beats.length;i++) {
			notes[i]= new Note(beats[i].getNoteLocation(), pane);
		}
		int i=0;
		music.changeFlag();
		music.start();
		for(i=0;i<notes.length;i++) {
			Thread.sleep(50);
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
