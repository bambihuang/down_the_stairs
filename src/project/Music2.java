
import java.io.File;//增加背景音樂

import javax.sound.sampled.AudioInputStream;//增加背景音樂
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music2 {
	//private Clip clip;
	
	public static void playSound() {

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music/8bitsloop_play.wav").getAbsoluteFile());// ...moding...
			Clip clip = AudioSystem.getClip();
			
			//if(x==1) {
				clip.open(audioInputStream);
				clip.start();
				//音樂音量控制
				FloatControl gainControl = (FloatControl)
				clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10.0f); 
				clip.loop(Clip.LOOP_CONTINUOUSLY);// loop
			/*}else if(x == 0) {
				long clipTimePosition = clip.getMicrosecondPosition();
				//clip.getMicrosecondPosition();
				clip.stop();
				clip.flush();
				clip.close();
			}*/
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/*public void stopSound() {

		try {
			this.clip.getMicrosecondPosition();
			clip.stop();
			clip.close();

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}*/

	
}