package Music;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	private boolean isMusicOn;
	Clip win,hit;

	
	public Music() {
		this.isMusicOn = true;	
	}
	
	public boolean isMusicOn() {
		return isMusicOn;
	}
	
	public void setMusic(boolean music) {
	    this.isMusicOn = music;
	}
 	
	public void playMusic(MusicTypes m) {
		String musicString = null;
		
		switch (m) {
			case HIT: {
				musicString = "./src/Music/hit.wav";
				break;
			}
			case WIN: {
				musicString = "./src/Music/gameover.wav";
				break;
			}
			case LOSE: {
				musicString = "./src/Music/gameover.wav";
				break;
			}
			case LOOP: {
				musicString = "./src/Music/loop.wav";
			}
		}
		

		try {
		    AudioInputStream audio = AudioSystem.getAudioInputStream(new File(musicString).getAbsoluteFile());
	        this.hit = AudioSystem.getClip();
	        hit.open(audio);
	        hit.start();
	        } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}

}
