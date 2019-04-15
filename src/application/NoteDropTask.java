package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NoteDropTask<V> extends Task<V> {

	ImageView imgView;
	AnchorPane pane;
	int y = 0;

	public NoteDropTask(ImageView imgView, AnchorPane pane) {
		this.imgView = imgView;
		this.pane = pane;
	}

	@Override
	protected V call() throws Exception {
		while (y < 1200) {
			y += Main.NOTE_SPEED;
			Platform.runLater(()-> imgView.setLayoutY(y));
			Thread.sleep(Main.SLEEP_TIME);
			updateProgress(y, 1200);
		}
		pane.getChildren().remove(imgView);
		return null;
	}

}
