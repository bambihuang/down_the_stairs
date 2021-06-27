package project;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music2 {
	public static Clip clip;
	
	public String getSoundPath() {
		try {
			URL url_music = getClass().getResource("music/8bitsloop_play.wav");
			String musicPath = URLDecoder.decode(url_music.getFile(), "UTF-8");
			return musicPath;
		} catch (Exception ex) {
			System.out.println("Error with get music path.");
			ex.printStackTrace();
			return "";
		}
	}
	public static void playSound() {
		
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music/8bitsloop_play.wav").getAbsoluteFile());// ...moding...
			clip = AudioSystem.getClip();
			
				clip.open(audioInputStream);
				clip.start();
				FloatControl gainControl = (FloatControl)
				clip.getControl(FloatControl.Type.MASTER_GAIN);
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