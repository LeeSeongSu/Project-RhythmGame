package application;

import java.io.File;
import java.io.FileInputStream;

import javafx.scene.image.Image;

/**
 * @author 태일
 * 이미지 파일을 불러와 javafx.scene.image.Image 클래스형의 객체로 반환시켜주기위한 클래스
 */
public class ImageParser {
	private String name;
	private File file;
	private FileInputStream fis;
	
	public ImageParser(String name) {
		try {
			file = new File(Main.class.getResource("../images/"+name).toURI());
			fis = new FileInputStream(file);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return new Image(fis);
	}
}
