package config;

import sound.Sound;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameConfiguration {
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	
	public static int BLOCK_SIZE=70;
	
	private static Sound s=new Sound();



	public static int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
	public static int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;
	
	public static int GAME_SPEED = 700;
	
	public static final void playmusic() {
		s.setFile(5);
		s.play();
		s.loop();
	}
	public static final void stopmusic() {
		s.stop();
	}
	

	


	

}
