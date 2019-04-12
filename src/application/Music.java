package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

/**
 * @author 태일 해당 클래스는 음악기능을 위한 클래스입니다.
 *
 */
public class Music extends Thread {

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private boolean flag=false;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
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
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
