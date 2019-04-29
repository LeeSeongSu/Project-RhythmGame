package application;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/**
 * 
  * @FileName : VoiceKeyListener.java
  * @Project : Project_DOS
  * @Date : 2019. 4. 19. 
  * @Author : Kim DongJin
  * @Comment :
 */
public class VoiceKeyListener extends Task<Void> {

	ImageView effectImgView;
	AnchorPane pane;
	static boolean lock = false;
	private static AudioFormat getAudioFormat() {
		float sampleRate = 8000.0F; // The number of samples that will be acquired
		// 8000,11025,16000,22050,44100 each second for each channel of audio data.
		int sampleSizeInBits = 16; // The number of bits that will be used to
		// 8,16 describe the value of each audio sample.
		int channels = 1; // Two channels for stereo, and one channel for mono.
		// 1,2
		boolean signed = true; // Whether the description of each audio sample
		// true,false
		// consists of both positive and negative values, or positive values only.
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
	}

	public VoiceKeyListener(AnchorPane pane) {
		this.pane = pane;
	}

	@Override
	 protected Void call() throws Exception {
		TargetDataLine line;
		AudioFormat audioFormat = getAudioFormat();
		boolean stopped = false;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
			// Handle the error ...

		}
		// Obtain and open the line.
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);

			// Assume that the TargetDataLine, line, has already
			// been obtained and opened.
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int numBytesRead;
			byte[] data = new byte[line.getBufferSize() / 7];
			short convert[] = new short[data.length];
			double voiceFreq = 0;
			// Begin audio capture.
			line.start();

			// Here, stopped is a global boolean set by another thread.
			while (!stopped) {
				// Read the next chunk of data from the TargetDataLine.
				numBytesRead = line.read(data, 0, data.length);
				// Save this chunk of data.
				out.write(data, 0, numBytesRead);
				try {
					int countzero = 0;

					for (int i = 0; i < data.length; i++) {
						// the loop that stores the whole audio data
						convert[i] = data[i];
						// to the convert variable which is a short data type,
						if (convert[i] == 0) {
							countzero++;
						}
						// then counts the number of zero's
					}
					voiceFreq = countzero;
					// calculates the number of frequency and
					// stores to the voiceFreq variable	
					if (voiceFreq >= 5&& voiceFreq <= 50) {
						if (!lock&&!pane.getChildren().contains(ImageStorage.effectImgView[3])) {
							lock=true;
							Platform.runLater(() -> pane.getChildren().add(ImageStorage.effectImgView[3]));
						}
						judge();
					} else {
						if (lock&&pane.getChildren().contains(ImageStorage.effectImgView[3])) {
							Platform.runLater(() -> pane.getChildren().remove(ImageStorage.effectImgView[3]));
							lock=false;
						}
					}
				
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (LineUnavailableException ex) {
			// Handle the error ...
		}
		return null;
	}

	public void judge() {
		Iterator<Note> iter = Game.noteList.iterator();
		while (iter.hasNext()) {
			Note note = iter.next();
			if (note.getX() == 3) {
				try {
					note.getTask().soundJudge();
					break;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
