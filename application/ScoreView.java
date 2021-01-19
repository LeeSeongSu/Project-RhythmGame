package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ScoreView {
	private int scores;
	private AnchorPane pane;
	private ImageView grade, album,homeBtnImgView;
	private BackgroundImage homeBtnBgImg;
	private Button exitBtn;
	private static boolean visited = false;

	public ScoreView(AnchorPane pane) {
		this.pane = pane;
		
		scores = Game.getScore();
		int perfects = NoteDropTask.getPerfect();
		int greats = NoteDropTask.getGreat();
		int goods = NoteDropTask.getGood();
		int bads = NoteDropTask.getBad();
		int misses = NoteDropTask.getMiss();
		double total = perfects + greats + goods + bads + misses; 
		
		double gradeCalc;
		int grade_A, grade_B, grade_C, grade_F, grade_S; 
		
		gradeCalc = (perfects + greats) / total ;
		

		
		Image homeImage = new ImageParser("HomeBtn.png").getImage();

		homeBtnBgImg = new BackgroundImage(homeImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, null);
		
		homeBtnImgView = new ImageView(homeImage);
		homeBtnImgView.setFitHeight(193);
		homeBtnImgView.setFitWidth(164);
		homeBtnImgView.setLayoutX(1705);
		homeBtnImgView.setLayoutY(32);
		homeBtnImgView.setOnMouseClicked(e -> exitBtnClick());
		pane.getChildren().add(homeBtnImgView);
		
		Label totalScore;
		Label perfect;
		Label great;
		Label good;
		Label bad;
		Label miss;

		totalScore = new Label(scores + "");
		totalScore.setFont(Font.font("210 OmniGothic 020", 70));
		totalScore.setTextFill(Color.GRAY);
		totalScore.setLayoutX(526);
		totalScore.setLayoutY(110);
		pane.getChildren().add(totalScore);

		perfect = new Label(perfects + "");
		perfect.setFont(Font.font("210 OmniGothic 020", 50));
		perfect.setTextFill(Color.GRAY);
		perfect.setLayoutX(826);
		perfect.setLayoutY(309);
		pane.getChildren().add(perfect);

		great = new Label(greats + "");
		great.setFont(Font.font("210 OmniGothic 020", 50));
		great.setTextFill(Color.GRAY);
		great.setLayoutX(726);
		great.setLayoutY(428);
		pane.getChildren().add(great);

		good = new Label(goods + "");
		good.setFont(Font.font("210 OmniGothic 020", 50));
		good.setTextFill(Color.GRAY);
		good.setLayoutX(676);
		good.setLayoutY(547);
		pane.getChildren().add(good);

		bad = new Label(bads + "");
		bad.setFont(Font.font("210 OmniGothic 020", 50));
		bad.setTextFill(Color.GRAY);
		bad.setLayoutX(626);
		bad.setLayoutY(666);
		pane.getChildren().add(bad);

		miss = new Label(misses + "");
		miss.setFont(Font.font("210 OmniGothic 020", 50));
		miss.setTextFill(Color.GRAY);
		miss.setLayoutX(676);
		miss.setLayoutY(785);
		pane.getChildren().add(miss);
				
		if(gradeCalc>0.9) {
			Image gradeImage = (new ImageParser("Grade_S.png").getImage());
			grade = new ImageView(gradeImage);
			grade.setLayoutX(115);
			grade.setLayoutY(764);
			pane.getChildren().add(grade);
			}
		else if (gradeCalc>0.8) {
			Image gradeImage = (new ImageParser("Grade_A.png").getImage());
			grade = new ImageView(gradeImage);
			grade.setLayoutX(115);
			grade.setLayoutY(764);
			pane.getChildren().add(grade);
		}
		else if (gradeCalc>0.7) {
			Image gradeImage = (new ImageParser("Grade_B.png").getImage());
			grade = new ImageView(gradeImage);
			grade.setLayoutX(115);
			grade.setLayoutY(764);
			pane.getChildren().add(grade);
		}
		else if (gradeCalc>0.6) {
			Image gradeImage = (new ImageParser("Grade_C.png").getImage());
			grade = new ImageView(gradeImage);
			grade.setLayoutX(115);
			grade.setLayoutY(764);
			pane.getChildren().add(grade);
		}
		else if(gradeCalc<=0.6){ 
			Image gradeImage = (new ImageParser("Grade_F.png").getImage());
			grade = new ImageView(gradeImage);
			grade.setLayoutX(115);
			grade.setLayoutY(764);
			pane.getChildren().add(grade);
			}
		
		String t = Game.getTitle();
		int idx = t.indexOf(".");
		String title = t.substring(0, idx);
		levelAndMoneyUp();
		Image album = (new ImageParser(title + ".jpg").getImage());
		Circle c = new Circle();
		c.setRadius(222);
		c.setLayoutX(1477);
		c.setLayoutY(539);
		c.setFill(new ImagePattern(album));
		pane.getChildren().add(c);

	}
	
	public void levelAndMoneyUp() {
		int value = scores/3000;
		String level;
		String money;
		String exp = Integer.parseInt(LoginSession.money)+value+"";
		if(Integer.parseInt(LoginSession.exp)+value>=1000) {
			exp = (Integer.parseInt(LoginSession.exp)+value-1000)+"";
			level = (Integer.parseInt(LoginSession.level)+1)+"";
		}else {
			exp = (Integer.parseInt(LoginSession.exp)+value)+"";
			level = LoginSession.level;
		}
		money = Integer.parseInt(LoginSession.money)+value+"";
		List<String> requestList = new ArrayList<>();
		requestList.add("memberId");
		requestList.add("email");
		requestList.add("token");
		requestList.add("nickname");
		requestList.add("money");
		requestList.add("level");
		requestList.add("exp");
		requestList.add("chooseEffect");
		requestList.add("chooseNote");
		Map<String, String> map = new HashMap<>();
		map.put("memberId", LoginSession.memberId);
		map.put("exp", exp);
		map.put("money", money);
		map.put("level", level);
		HttpConnector hc = new HttpConnector("endGame", map);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					Map<String, String> result = hc.request(requestList);
					LoginSession.memberId=result.get("memberId");
					LoginSession.email=result.get("email");
					LoginSession.token=result.get("token");
					LoginSession.nickname=result.get("nickname");
					LoginSession.money=result.get("money");
					LoginSession.level=result.get("level");
					LoginSession.chooseEffect=result.get("chooseEffect");
					LoginSession.chooseNote=result.get("chooseNote");
					LoginSession.exp=result.get("exp");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	public void exitBtnClick() {
		Stage stage = (Stage) homeBtnImgView.getScene().getWindow();

		Music music = Game.getMusic();
		music.close();

		if (!visited) {
			DOSApplicationController.introMusic = new Music("Game On.mp3", true);
			try {
//			Thread.sleep(4500);
				DOSApplicationController.introMusic.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

			visited = true;
		}


		try {

			AnchorPane second = FXMLLoader.load(getClass().getResource("/view/SelectScreen.fxml"));

			LobbyView v = new LobbyView(second);
			Menubar menubar = new Menubar(second, 0);

			Scene c = new Scene(second);

			stage.setScene(c);

			stage.show();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
