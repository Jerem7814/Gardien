package process;

import org.apache.log4j.Logger;


import log.LoggerUtility;
import map.Block;
import model.Intruder;

/**
 * This utility class allows the creation of intruders position. The class uses simple factory design pattern with static utility methods.
 * 
 * The creation of the intruders position is recorded by Log4j.
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */


public class IntruderPositionFactory {

	/**
	 * Retrieves the logger for writing logs in
	 * 
	 * 1) a text file ("text" for the logger name) 2) or in a html file ("html" for the logger name);
	 */
	private static Logger logger = LoggerUtility.getLogger(IntruderPositionFactory.class, "html");



	/**
	 * Creates an intruder.
	 * 
	 * @param block the position of the initialized intruder
	 * @return the intruder with his position
	 */
	public static Intruder createIntruder(Block block) {
		logger.info("Intruder creation with values : " + block);
		return new Intruder(block);
	}

	
}
