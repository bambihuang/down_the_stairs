package project;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music2 {
	public static Clip clip;

	public static void playSound() {

		try {
			InputStream in = Music2.class.getResourceAsStream("/music/8bitsloop_play.wav");
			InputStream bufferedIn = new BufferedInputStream(in);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
			//AudioInputStream audioInputStream = AudioSystem
			//		.getAudioInputStream(new File("music/8bitsloop_play.wav").getAbsoluteFile());// ...moding...
			clip = AudioSystem.getClip();

			clip.open(audioInputStream);
			clip.start();
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			System.out.println("Error with sound.");
			ex.printStackTrace();
		}
	}

	public static void stopSound() {

		try {
			clip.getMicrosecondPosition();
			clip.stop();
			clip.close();

		} catch (Exception ex) {
			System.out.println("Error with sound.");
			ex.printStackTrace();
		}
	}

}