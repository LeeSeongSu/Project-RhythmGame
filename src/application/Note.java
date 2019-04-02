package application;

/**
 * @author xoxod
 * @author realmingyeong
 *
 */

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	// 노트 이미지
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/note.png")).getImage();
	private int x, y; // 노트의 현재 위치

	public Note(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 노트에 대한 이미지 그려주기
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null);
	}
	
	@Override
	public void run() {}

}
