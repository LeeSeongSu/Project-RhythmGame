package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * @author 태일 해당 클래스는 음악기능을 위한 클래스입니다.
 *
 */
public class Music extends Thread {

	private boolean isLoop;
	private File file;
	private boolean flag=false;
	private Media media;
	private MediaPlayer mediaPlayer;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Class.forName("application.DOSApplication").getResource("../music/" + name).toURI());
			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTime() {
		if (mediaPlayer == null)
			return 0;
		return (int) mediaPlayer.getCurrentTime().toMillis();
	}
	
	public void silence() {
		mediaPlayer.setVolume(0);
	}
	
	public void normalVolume() {
		mediaPlayer.setVolume(1);
	}
	
	public void setVolume(double volume) {
		mediaPlayer.setVolume(volume);
	}
	
	public double getVolume() {
		return mediaPlayer.getVolume();
	}

	public void close() {
		isLoop = false;
		mediaPlayer.stop();
		this.interrupt();
	}
	
	public void changeFlag() {
		if(flag)
			flag=false;
		else
			flag=true;
	}

	@Override
	public void run() {
		try {
			if(flag) {
				Thread.sleep(2000);
			}
			do {
				mediaPlayer.play();

			} while (isLoop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
