package model;


import map.Block;

/**
* this represnts all our items (potions, nets, cloak, lure..)
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/

public class Item extends MElement{
	private int nbre;
	private String name;
	

	/**
	 * calls the position of the superclass, build an item
	 * @param position The position of the item
	 * @param nbre The quantity of the items
	 * @param name The name of the item
	 */
	public Item(Block position, int nbre, String name) {
		super(position);
		this.nbre = nbre;
		this.name = name;
	}
	
	/**
	 * calls the position of the superclass, build an item without quantity (quantity=0)
	 * @param position Position of the item
	 * @param name Name of the item
	 */
	public Item(Block position, String name) {
		super(position);
		this.nbre = 0;
		this.name = name;
	}

	/**
	 * get the quantity of items
	 * @return the number of items
	 */
	public int getNbre() {
		return nbre;
	}
	/**
	 * set the quantity of items
	 * @param nbre Number of items
	 */	
	public void setNbre(int nbre) {
		this.nbre = nbre;
	}


	/**
	 * get the name of items
	 * @return the name of items
	 */

	public String getName() {
		return name;
	}

	/**
	 * set the name of items
	 * @param name of the item
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * increment the number given
	 * @param n The number
	 */
	public void increment(int n) {
		nbre+=n;
	}
	
	/**
	 * decrement the number given
	 * @param n The number
	 */
	public void decrement(int n) {
		nbre-=n;
	}




	
	
}
