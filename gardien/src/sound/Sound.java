package sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[]=new URL[15];
	
	public Sound(){
		soundURL[0]=getClass().getResource("/sound/coin.wav");
		soundURL[1]=getClass().getResource("/sound/potion.wav");
		soundURL[2]=getClass().getResource("/sound/police.wav");
		soundURL[3]=getClass().getResource("/sound/laugh.wav");
		soundURL[4]=getClass().getResource("/sound/item.wav");
		soundURL[5]=getClass().getResource("/sound/musicf.wav");
		soundURL[6]=getClass().getResource("/sound/glaugh.wav");
		soundURL[7]=getClass().getResource("/sound/open.wav");
		soundURL[8]=getClass().getResource("/sound/incendie.wav");
		soundURL[9]=getClass().getResource("/sound/netcapture.wav");



	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			}catch(Exception e) { 
				
			}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	


}
