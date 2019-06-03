package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class NoteEffectKeyListener implements EventHandler<KeyEvent> {
	AnchorPane pane;
	ImageView effectImgView;
	ImageStorage storage = new ImageStorage();

	public NoteEffectKeyListener(AnchorPane pane) {
		this.pane = pane;
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
		case S:
			removeEffect(0);
			break;
		case D:
			removeEffect(1);
			break;
		case F:
			removeEffect(2);
			break;

		case J:
			removeEffect(4);
			break;
		case K:
			removeEffect(5);
			break;
		case L:
			removeEffect(6);
			break;
		}
		if (LobbyView.mode_voice == "none") {
			switch (event.getCode()) {
			case SPACE:
				removeEffect(3);

				break;
			}
		}
	}

	public void removeEffect(int index) {
		effectImgView = storage.effectImgView[index];
		pane.getChildren().remove(effectImgView);
	}

}
