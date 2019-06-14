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

public class MakeGameTask extends Task<Void> {
	Beat[] beats;
	Note[] notes;
	Music music;
	AnchorPane pane;
	ArrayList<Note> noteList;

	public MakeGameTask(Music music, AnchorPane pane, ArrayList<Note> noteList) {
		this.music = music;
		this.pane = pane;
		this.noteList = noteList;
	}

	@Override
	protected Void call() throws Exception {
		if (Game.getTitle().equals("Invincible - Deaf Kev.mp3")) {
			beats = new Beat[] {
					// 느린 부분
					new Beat(1, 0), new Beat(600, 2), new Beat(1200, 1), new Beat(1800, 3), new Beat(2400, 2),
					new Beat(3000, 4), new Beat(3600, 3), new Beat(4200, 5), new Beat(4800, 4), new Beat(5400, 6),
					new Beat(6000, 4), new Beat(6600, 5), new Beat(7200, 3), new Beat(7800, 4), new Beat(8400, 2),
					new Beat(10800, 3), new Beat(11100, 1), new Beat(11400, 2), new Beat(11700, 0), new Beat(12000, 3),
					new Beat(12300, 4), new Beat(12600, 6), new Beat(12900, 0), new Beat(13200, 2), new Beat(13500, 3),
					new Beat(13800, 5), new Beat(14100, 2), new Beat(14400, 0), new Beat(14700, 5), new Beat(15000, 6),
					new Beat(15300, 2), new Beat(15600, 0), new Beat(15900, 1), new Beat(16200, 5), new Beat(16500, 3),
					new Beat(16800, 4), new Beat(17100, 1), new Beat(17400, 5), new Beat(17700, 2), new Beat(18000, 4),
					new Beat(18300, 2), new Beat(18500, 0), new Beat(18800, 3), new Beat(19100, 2), new Beat(19400, 3),
					new Beat(19700, 1), new Beat(20000, 3), new Beat(20300, 6), new Beat(20600, 5), new Beat(20900, 3),
					new Beat(21200, 5), new Beat(21500, 0), new Beat(21800, 2), new Beat(22100, 3), new Beat(22400, 4),
					new Beat(22700, 5), new Beat(23000, 1), new Beat(23300, 2), new Beat(23600, 6), new Beat(23900, 3),
					new Beat(24200, 5), new Beat(24500, 0), new Beat(24800, 6), new Beat(25100, 0), new Beat(25400, 3),
					new Beat(25700, 1), new Beat(26000, 3), new Beat(26300, 2), new Beat(26600, 1), new Beat(26900, 6),
					new Beat(27200, 5), new Beat(27500, 0), new Beat(27800, 4), new Beat(28100, 3), new Beat(28400, 4),
					new Beat(28700, 5), new Beat(29000, 6), new Beat(29300, 2), new Beat(29600, 1), new Beat(29900, 3),
					new Beat(30200, 5), new Beat(30500, 0), new Beat(30800, 3), new Beat(31100, 2), new Beat(31400, 1),
					new Beat(31700, 5), new Beat(32000, 6), new Beat(32300, 2), new Beat(32600, 4), new Beat(32900, 3),
					new Beat(33200, 5), new Beat(33500, 0), new Beat(33800, 6), new Beat(34100, 1), new Beat(34400, 1), /// 3개
																														/// 동시
					new Beat(34400, 3), new Beat(34400, 5),

					new Beat(35600, 2), new Beat(35750, 1), new Beat(35900, 5), new Beat(36050, 3), new Beat(36200, 0),
					new Beat(36350, 3), new Beat(36500, 5), new Beat(36650, 2), new Beat(36800, 3), new Beat(36950, 4),
					new Beat(37100, 3), new Beat(37250, 5), new Beat(37500, 3), new Beat(37650, 2), new Beat(37800, 3),
					new Beat(37950, 0), new Beat(38100, 1), new Beat(38250, 2), new Beat(38400, 5), new Beat(38550, 2),
					new Beat(38700, 3), new Beat(38850, 4), new Beat(39000, 6), new Beat(39150, 3), new Beat(39300, 2),
					new Beat(39450, 1), new Beat(39600, 3), new Beat(39750, 0), new Beat(40900, 3), new Beat(41050, 0),
					new Beat(41200, 2), new Beat(41350, 0), new Beat(41500, 4), new Beat(41650, 5), new Beat(41800, 6),
					new Beat(41950, 0), new Beat(42100, 3), new Beat(42250, 0), new Beat(42400, 1), new Beat(42550, 0),
					new Beat(42700, 2), new Beat(42850, 0), new Beat(43000, 4), new Beat(44150, 3), new Beat(44300, 6),
					new Beat(44450, 1), new Beat(44600, 5), new Beat(44750, 1), new Beat(44900, 5), new Beat(45050, 2),
					new Beat(45200, 5), new Beat(45350, 6), new Beat(45500, 2), new Beat(45650, 4), new Beat(45800, 0),
					new Beat(45950, 5), new Beat(46100, 0), new Beat(46250, 1), new Beat(46400, 0), new Beat(46550, 3),
					new Beat(46700, 5), new Beat(46850, 4), new Beat(47000, 2), new Beat(47150, 3), new Beat(47300, 5),
					new Beat(47450, 1), new Beat(47600, 3), new Beat(47750, 6), new Beat(47900, 4), new Beat(48050, 0),
					new Beat(48200, 5), new Beat(48350, 0), new Beat(48500, 2), new Beat(48650, 0), new Beat(48800, 1),
					new Beat(48950, 4), new Beat(49100, 0), new Beat(49250, 4), new Beat(49400, 0), new Beat(49550, 2),
					new Beat(49700, 0), new Beat(49850, 1), new Beat(50000, 5), new Beat(50150, 3), new Beat(50300, 5),
					new Beat(50450, 3), new Beat(50600, 6), new Beat(50750, 2), new Beat(50900, 1), new Beat(51050, 0),
					new Beat(51200, 3), new Beat(51350, 6), new Beat(51500, 4), new Beat(51650, 0), new Beat(51800, 2),
					new Beat(51950, 0), new Beat(52100, 4), new Beat(52250, 5), new Beat(52400, 1), new Beat(52550, 0),
					new Beat(52700, 2), new Beat(52850, 6), new Beat(53000, 4), new Beat(53150, 3), new Beat(53300, 2),
					new Beat(53450, 1), new Beat(53600, 3), new Beat(53750, 4), new Beat(53900, 1), new Beat(54050, 6),
					new Beat(54200, 2), new Beat(54350, 3), new Beat(54500, 0), new Beat(54650, 6), new Beat(54800, 4),
					new Beat(54950, 2), new Beat(55100, 0), new Beat(55250, 1), new Beat(55400, 5), new Beat(55550, 2),
					new Beat(55700, 3), new Beat(55850, 2), new Beat(56000, 1), new Beat(56150, 5), new Beat(56300, 2),
					new Beat(56450, 6), new Beat(56600, 3), new Beat(56750, 5), new Beat(56900, 4), new Beat(57050, 1),
					new Beat(57200, 3), new Beat(57350, 4), new Beat(57500, 5), new Beat(57650, 1), new Beat(57800, 6),
					new Beat(57950, 2), new Beat(58100, 4), new Beat(58250, 3), new Beat(58400, 0), new Beat(58550, 1),
					new Beat(58700, 2), new Beat(58850, 0), new Beat(59000, 3), new Beat(59150, 5), new Beat(59300, 3),
					new Beat(59450, 6), new Beat(59600, 3), new Beat(59600, 1), new Beat(59600, 5),

			};

		} else if (Game.getTitle().equals("Disfigure - Blank.mp3")) {
			beats = new Beat[] { new Beat(1850, 0), new Beat(2720, 2), new Beat(3580, 4), new Beat(4450, 5),
					new Beat(5260, 3), new Beat(6150, 6), new Beat(6950, 4), new Beat(7820, 3), new Beat(8640, 1),
					new Beat(9530, 2), new Beat(10400, 5), new Beat(11270, 4), new Beat(12140, 3), new Beat(12950, 6),
					new Beat(13420, 3), new Beat(13820, 2), new Beat(14310, 5), new Beat(14710, 3), new Beat(15110, 1),
					new Beat(15510, 5),
					// 빠른부분
					new Beat(16000, 0), new Beat(16250, 3), new Beat(16500, 0), new Beat(16750, 3), new Beat(17000, 4),
					new Beat(17250, 6), new Beat(17500, 5), new Beat(17750, 1), new Beat(18000, 3), new Beat(18250, 0),
					new Beat(18500, 0), new Beat(18750, 3), new Beat(19000, 3), new Beat(19250, 6), new Beat(19500, 5),
					new Beat(19750, 1), new Beat(20000, 3), new Beat(20250, 0), new Beat(20500, 0), new Beat(20750, 3),
					new Beat(21000, 3), new Beat(21250, 6), new Beat(21500, 5), new Beat(21750, 1), new Beat(22000, 3),
					new Beat(22250, 0), new Beat(22500, 6), new Beat(22750, 3), new Beat(23000, 3), new Beat(23250, 6),
					new Beat(23500, 5), new Beat(23750, 1), new Beat(24000, 3), new Beat(24250, 0), new Beat(24500, 4),
					new Beat(24750, 3), new Beat(25000, 3), new Beat(25250, 6), new Beat(25500, 5), new Beat(25750, 1),
					new Beat(26000, 3), new Beat(26250, 0), new Beat(26500, 1), new Beat(26750, 3), new Beat(27000, 2),
					new Beat(27250, 6), new Beat(27500, 5), new Beat(27750, 1), new Beat(28000, 3), new Beat(28250, 6),
					new Beat(28500, 5), new Beat(28750, 1), new Beat(29000, 3), new Beat(29250, 1), new Beat(29250, 2),
					new Beat(29250, 4), new Beat(29250, 5), new Beat(29500, 1), new Beat(29750, 3), new Beat(29950, 3),
					new Beat(30250, 6), new Beat(30350, 5), new Beat(30450, 1), new Beat(30700, 6), new Beat(30950, 5),
					new Beat(31150, 1), new Beat(31400, 6), new Beat(31950, 5), new Beat(32100, 3), new Beat(32300, 4),
					new Beat(32550, 3), new Beat(32800, 0), new Beat(33050, 1), new Beat(33300, 3), new Beat(33750, 3),
					new Beat(34000, 3), new Beat(34250, 0), new Beat(34500, 1), new Beat(34750, 3), new Beat(35000, 3),
					new Beat(35250, 3), new Beat(35500, 0), new Beat(35750, 1), new Beat(36000, 3), new Beat(36250, 3),
					new Beat(36500, 3), new Beat(36750, 0), new Beat(37000, 1), new Beat(37250, 5), new Beat(37500, 6),
					new Beat(37750, 3), new Beat(38000, 1), new Beat(38250, 2), new Beat(38500, 3), new Beat(38750, 0),
					new Beat(39000, 1), new Beat(39250, 5), new Beat(39500, 3), new Beat(39750, 0), new Beat(40000, 1),
					new Beat(40250, 5), new Beat(40500, 3), new Beat(40750, 0), new Beat(41000, 1), new Beat(41250, 3),
					new Beat(41500, 2), new Beat(41750, 5), new Beat(42000, 1), new Beat(42250, 4), new Beat(42500, 3),
					new Beat(42750, 0), new Beat(43000, 1), new Beat(43250, 4), new Beat(43500, 5), new Beat(43750, 0),
					new Beat(44000, 1), new Beat(44250, 2), new Beat(44500, 3), new Beat(44750, 0), new Beat(45000, 1),
					new Beat(45250, 3), new Beat(45500, 4), new Beat(45750, 5), new Beat(46000, 1), new Beat(46250, 3),
					new Beat(46500, 5), new Beat(46750, 0), new Beat(47000, 1), new Beat(47250, 6), new Beat(47500, 3),
					new Beat(47750, 0), new Beat(48000, 5), new Beat(48250, 2), new Beat(48500, 3), new Beat(48750, 0),
					new Beat(49000, 1), new Beat(49250, 3), new Beat(49500, 4), new Beat(49750, 0), new Beat(50000, 1),
					new Beat(50250, 6), new Beat(50500, 3), new Beat(50750, 2), new Beat(51000, 1), new Beat(51250, 3),
					new Beat(51500, 2), new Beat(51750, 5), new Beat(52000, 2), new Beat(52250, 4), new Beat(52500, 3),
					new Beat(52750, 2), new Beat(53000, 1), new Beat(53250, 2), new Beat(53500, 1), new Beat(53750, 6),
					new Beat(54000, 1), new Beat(54250, 3), new Beat(54500, 6), new Beat(54750, 0), new Beat(55000, 6),
					new Beat(55250, 3), new Beat(55500, 5), new Beat(55750, 6), new Beat(56000, 1), new Beat(56250, 3),
					new Beat(56500, 5), new Beat(56750, 6), new Beat(57000, 1), new Beat(57250, 3), new Beat(57500, 3),
					new Beat(57750, 5), new Beat(58000, 1), new Beat(58250, 3), new Beat(58500, 2), new Beat(58750, 0),
					new Beat(59000, 1), new Beat(59250, 3), new Beat(59500, 5), new Beat(59750, 0), new Beat(60000, 1),
					new Beat(60250, 3), new Beat(60500, 5), new Beat(60750, 0), new Beat(61000, 1), new Beat(61250, 3),
					new Beat(61500, 0), new Beat(61750, 5), new Beat(62000, 1), new Beat(62250, 3), new Beat(62500, 3),
					new Beat(62750, 0), new Beat(63000, 1), new Beat(63250, 3), new Beat(63500, 3), new Beat(63750, 5),
					new Beat(64000, 1), new Beat(64250, 0), new Beat(64500, 3), new Beat(64750, 2), new Beat(65000, 1),
					new Beat(65250, 4), new Beat(65500, 3), new Beat(65750, 4), new Beat(66000, 5), new Beat(66250, 3),
					new Beat(66500, 3), new Beat(66750, 2), new Beat(67000, 1), new Beat(67250, 3), new Beat(67500, 3),
					new Beat(67750, 2), new Beat(68000, 4), new Beat(68250, 3), new Beat(68500, 3), new Beat(68750, 0),
					new Beat(69000, 5), new Beat(69250, 3), new Beat(69500, 6), new Beat(69750, 0), new Beat(70000, 1),
					new Beat(70250, 2), new Beat(70450, 3), new Beat(70450, 2), new Beat(70450, 1), new Beat(70450, 4),
					new Beat(70450, 5), new Beat(70450, 6),

			};
		} else if (Game.getTitle().equals("Elektronomia - Sky High.mp3")) {
			beats = new Beat[] { new Beat(1000, 0), new Beat(2880, 2), new Beat(4760, 4), new Beat(6640, 5),
					new Beat(8520, 3), new Beat(10400, 6), new Beat(12280, 6), new Beat(14160, 4), new Beat(16040, 3),
					new Beat(17920, 1), new Beat(19800, 2), new Beat(21680, 5), new Beat(23560, 4), new Beat(25400, 3),
					new Beat(27320, 6), new Beat(29200, 3), new Beat(31080, 2), new Beat(32880, 5), new Beat(34760, 3),
					new Beat(36560, 1), new Beat(38440, 5), new Beat(40320, 0), new Beat(42200, 3), new Beat(44080, 0),
					new Beat(45960, 3),

					new Beat(47840, 4), new Beat(48780, 4), new Beat(49720, 6), new Beat(50660, 4), new Beat(51600, 5),
					new Beat(52540, 4), new Beat(53480, 1), new Beat(54420, 4), new Beat(55360, 3), new Beat(56330, 4),
					new Beat(57240, 0), new Beat(59120, 0), new Beat(60060, 4), new Beat(61000, 3), new Beat(61940, 3),
					new Beat(62880, 1), new Beat(63820, 3), new Beat(64760, 5), new Beat(65700, 5), new Beat(66640, 0),
					new Beat(67580, 5), new Beat(68520, 3), new Beat(70400, 0), new Beat(71340, 2), new Beat(72280, 3),
					new Beat(73220, 5), new Beat(74160, 4), new Beat(75100, 4), new Beat(75960, 6),

			};
		}

		Platform.runLater(() -> pane.getChildren().add(ImageStorage.judgeImgView));
		Note[] notes = new Note[beats.length];
		for (int i = 0; i < beats.length; i++) {
			notes[i] = new Note(beats[i].getNoteLocation(), pane);
			noteList.add(notes[i]);
		}
		int i = 0;
		music.changeFlag();
		music.start();
		for (i = 0; i < notes.length; i++) {
			Thread.sleep(1);
			if (beats[i].getTime() <= music.getTime()) {
				Note tmp = notes[i];
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						tmp.screenDraw();
						tmp.setDaemon(true);
						tmp.start();
					}
				});
			} else {
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
