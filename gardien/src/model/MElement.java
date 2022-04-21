package model;

import map.Block;

/**
* this abstract class is composed of blocks which allow us to position our data on the map
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/

public abstract class MElement {
	private Block position;

	/**
	 * build the MElement
	 * @param position A block which gives us a position
	 */	
	public MElement(Block position) {
		this.position = position;
	}
	/**
	 * get the position of the object on the map
	 * @return the position of the object on the map
	 */

	public Block getPosition() {
		return position;
	}

	/**
	 * set the position of the object on the map
	 * @param position Block which gives us a position
	 */	
	public void setPosition(Block position) {
		this.position = position;
	}

}
