package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import map.Block;

/**
* this class allows to initialize the guardians on the map
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/

public class Guardian extends MElement{
	private int vision;
	private int agility;
	private HashMap<String, Item> items;
	private int precision;
	private List<Block> visionZone;


	/**
	 * build a guardian thanks to the position, vision, agility, precision, list of items of the guardian
	 * @param position Position of guardian
	 * @param vision Vision of guardian
	 * @param agility Agility of guardian
	 * @param precision Precision of guardian
	 * @param items List of items of the guardian
	 */
	
	public Guardian(Block position, int vision, int agility, HashMap<String, Item> items, int precision) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		this.items = items;
		this.precision = precision;
	}
	
	/**
	 * build a guardian thanks to the position, vision, agility and precision of the guardian
	 * @param position Position of guardian
	 * @param vision Vision of guardian
	 * @param agility Agility of guardian
	 * @param precision Precision of guardian
	 */
	public Guardian(Block position, int vision, int agility, int precision) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		items = new HashMap<String,Item>();
		this.precision = precision;
	}
	
	/**
	 * build a guardian thanks to the position of the guardian
	 * @param position Block of position of the guardian
	 */ 
	public Guardian(Block position) {
		super(position);
		this.vision = 0;
		this.agility = 0;
		items = new HashMap<String,Item>();
		this.precision = 0;
		visionZone = new ArrayList<Block>();
	}
	/**
	 * initialize the items of the guardian at the beggining of the game with his attributes
	 */

	public void inititem() {
		String pA="Agility Potion";
		int nbpA=0;
		Item ap=new Item(null,nbpA,pA);
		String pV="Vision Potion";
		int nbpV=0;
		Item vp=new Item(null,nbpV,pV);
		String pP="Precision Potion";
		int nbpP=0;
		Item pp=new Item(null,nbpP,pP);
		String filet="Filet";
		int nbF=1;
		Item fil=new Item(null,nbF,filet);
		String sifflet="Sifflet";
		int nbS=1;
		Item sif=new Item(null,nbS,sifflet);
		items.put(filet, fil);
		items.put(pP, pp);
		items.put(pV, vp);
		items.put(pA, ap);	
		items.put(sifflet, sif);
		vision=1;
		agility=1;
		precision=5; 

		setVisionzone();
	}

	/**
	 * set Vision Zone of the guardian with his vision attribute which allows him to see in row and column
	 */
	public void setVisionzone() {
		visionZone.clear();
		int line = super.getPosition().getLine();
		int column = super.getPosition().getColumn();
		for(int i=1;i<=vision;i++) {
			Block bas=new Block(line+i,column);
			Block haut=new Block(line-i,column);
			Block gauche=new Block(line,column-i);
			Block droite=new Block(line,column+i);
			
			if(line-i>-1) {
				visionZone.add(haut);
			}
			if(line+i<GameConfiguration.LINE_COUNT) {
				visionZone.add(bas);
			}
			if(column-i>-1) {
				visionZone.add(gauche);
			}
			if(column+i<GameConfiguration.COLUMN_COUNT) {
				visionZone.add(droite);
			}
		}
	}

	/**
	 * get the Vision of the guardian
	 * @return vision of the guardian
	 */
	public int getVision() {
		return vision;
	}
	/**
	 * set the vision of the guardian
	 * @param vision attribute
	 */

	public void setVision(int vision) {
		this.vision = vision;
	}

	/**
	 * get the agility of the guardian
	 * @return agility of the guardian
	 */
	public int getAgility() {
		return agility;
	}

	/**
	 * set agility of the guardian
	 * @param agility attribute
	 */
	public void setAgility(int agility) {
		this.agility = agility;
	}

	/**
	 * get the item list of the guardian
	 * @return the item list of the guardian
	 */
	public HashMap<String, Item> getItems() {
		return items;
	}

	/**
	 * set the items list of the guardian
	 * @param items HashMap of String and Item 
	 */
	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}

	/**
	 * get the vision zone of the guardian
	 * @return vision zone of the guardian
	 */
	
	public List<Block> getVisionzone() {
		return visionZone;
	}
	/**
	 * get the precision attribute
	 * @return precision of the guardian
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * set the precision attribute with the number given
	 * @param precision A number
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	/**
	 * increment the agility given
	 * @param n A number
	 */
	public void incrementA(int n) {
		agility+=n;
	}
	/**
	 * decrement the agility given
	 * @param n A number
	 */
	public void decrementA(int n) {
		agility-=n;
	}
	/**
	 * increment the vision given
	 * @param n A number
	 */
	public void incrementV(int n) {
		vision+=n;
	}
	/**
	 * decrement the vision given
	 * @param n A number
	 */
	public void decrementV(int n) {
		vision-=n;
	}
	/**
	 * increment the precision given
	 * @param n A number
	 */
	public void incrementP(int n) {
		precision+=n;
	}
	/**
	 * decrement the precision given
	 * @param n A number
	 */
	public void decrementP(int n) {
		precision-=n;
	}



	


	

}
