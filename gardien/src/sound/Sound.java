package sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
* this class manage the sound of our program. if there is a javax.Sound error, you have to configure BuildPath of the project. BuildPath -} configure BuildPath -} source -} add folder -} tick ressources -} valid  
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class Sound {
	Clip clip;
	URL soundURL[]=new URL[15];
	
	 /**
	 * initialize the sounds in the URL[]
	 */ 
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
		soundURL[10]=getClass().getResource("/sound/sifflet.wav");



	}
	
	/**
	 * set the music selected 
	 * @param i The index of the sound selected in the URL[]
	 */
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
			}catch(Exception e) { 
				
			}
	}
	
	/**
	 * play the music
	 */
	public void play() {
		clip.start();
	}
	
	/**
	 * loop the music selected 
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * stop the music selected 
	 */
	public void stop() {
		clip.stop();
	}
	


}
