package config;

import sound.Sound;

/**
* configurations of the simulation 
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class GameConfiguration {
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	
	public static int BLOCK_SIZE=70;
	
	private static Sound s=new Sound();
	private static Sound s2=new Sound();




	public static int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
	public static int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;
	
	public static int GAME_SPEED = 700;
	/**
	 * starts the main theme music of the simulation/game
	 */
	public static final void playmusic() {
		s.setFile(5);
		s.play();
		s.loop();
	}
	/**
	 * stop the main theme music of the simulation/game
	 */
	public static final void stopmusic() {
		s.stop();
	}
	
	/**
	 * starts the open theme music of the simulation/game
	 */
	public static final void playmusicOpen() {
		s2.setFile(7);
		s2.play();
		s2.loop();
	}
	/**
	 * stop the main open music of the simulation/game
	 */
	public static final void stopmusicOpen() {
		s2.stop();
	}
	

	


	

}
