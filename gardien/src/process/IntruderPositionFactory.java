package process;

import org.apache.log4j.Logger;


import log.LoggerUtility;
import map.Block;
import model.Intruder;

/**
 * This utility class allows the creation of tree node of any concrete type. The class uses simple factory design pattern with static utility methods.
 * 
 * The creation of the tree nodes is recorded by Log4j.
 * 
 * @author Tianxiao.Liu@u-cergy.fr
 */
public class IntruderPositionFactory {

	/**
	 * Retrieves the logger for writing logs in
	 * 
	 * 1) a text file ("text" for the logger name) 2) or in a html file ("html" for the logger name);
	 */
	private static Logger logger = LoggerUtility.getLogger(IntruderPositionFactory.class, "html");



	/**
	 * Creates a Constant node.
	 * 
	 * @param value the constant integer value
	 * @return the constructed node
	 */
	public static Intruder createIntruder(Block block) {
		logger.info("Intruder creation with values : " + block);
		return new Intruder(block);
	}

	
}
