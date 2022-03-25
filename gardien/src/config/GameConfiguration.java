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
	



	public static int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
	public static int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;
	
	public static int GAME_SPEED = 400;
	
	public static final void playmusic() {
		Sound s=new Sound();
		s.setFile(5);
		s.play();
		s.loop();
	}
	

	


	

}
