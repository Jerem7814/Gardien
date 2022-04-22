package model;

import map.Block;

/**
* set the position of the exitgate
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/

public class ExitGate extends MElement{
	/**
	 * build the exitgate on the map
	 * @param position The block which set the position
	 */
	public ExitGate(Block position) {
		super(position);
	}
}