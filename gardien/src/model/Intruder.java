package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import map.Block;

/**
* this class allows to initialize the intruders on the map and initialize it
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class Intruder extends MElement{
	private int vision;
	private int agility;
	private HashMap<String, Item> items;
	public int precision;
	public int moneyearned;
	private List<Block> visionZone;
	private boolean isTransparent;
	private int transparentcount;

	/**
	 * build an intruder thanks to the position, vision, agility, dodge, list of items and the money of the intruder
	 * @param position Position of the intruder
	 * @param vision Vision of the intruder
	 * @param agility Agility of the intruder
	 * @param dodge Dodge of the intruder
	 * @param items List of items 
	 * @param moneyearned The money of the intruder
	 */
	
	public Intruder(Block position, int vision, int agility, HashMap<String, Item> items, int dodge, int moneyearned) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		this.items = items;
		this.precision = dodge;
		this.moneyearned = moneyearned;
	}
	
	/**
	 * build an intruder thanks to the position of the intruder
	 * @param position The position of the intruder
	 */
	
	public Intruder(Block position) {
		super(position);
		this.vision = 0;
		this.agility = 0;
		items = new HashMap<String,Item>();
		this.precision = 0;
		this.moneyearned = 0;
		visionZone = new ArrayList<Block>();
	}
	/**
	 * initialize the items of the intruder at the beggining of the game with his attributes
	 */
	public void inititem() {
		String pA="Agility Potion";
		int nbpA=0;
		Item ap=new Item(null,nbpA,pA);
		String pV="Vision Potion";
		int nbpV=0;
		Item vp=new Item(null,nbpV,pV);
		String pR="Precision Potion";
		int nbpR=0;
		Item rp=new Item(null,nbpR,pR);
		String iC="Invisibility cloak";
		int nbiC=1;
		Item invisible=new Item(null,nbiC,iC);
		String lure="Lure";
		int nbl=1;
		Item l=new Item(null,nbl,lure);
		String money="Money";
		int nbM=0;
		Item dollars=new Item(null,nbM,money);
		items.put(lure, l);
		items.put(iC, invisible);
		items.put(pR, rp);
		items.put(pV, vp);
		items.put(pA, ap);
		items.put(money, dollars);
		moneyearned=0;
		vision=3;
		agility=2;
		precision=getRandomNumber(3,5);
		this.isTransparent=false;
		this.transparentcount=15;
		setVisionzone();
		
	}
	
	/**
	 * verify if the intruder is transparent
	 * @return boolean true or false 
	 */
	
	public boolean isTransparent() {
		return isTransparent;
	}

	/**
	 * set the Transparency of the intruder if param given is true
	 * @param isTransparent Boolean of isTransparent
	 */
	public void setTransparent(boolean isTransparent) {
		this.isTransparent = isTransparent;
	}
	/**
	 * get the dodge attribute
	 * @return dodge of the intruder
	 */
	public int getDodge() {
		return precision;
	}

	/**
	 * set the precision attribute with the number given
	 * @param dodge The number of dodge
	 */
	public void setDodge(int dodge) {
		this.precision = dodge;
	}
	/**
	 * get the money earned of the intruder
	 * @return money earned of the intruder
	 */

	public int getMoneyearned() {
		return moneyearned;
	}
	/**
	 * set the money earned of the intruder
	 * @param moneyearned Number of money
	 */

	public void setMoneyearned(int moneyearned) {
		this.moneyearned = moneyearned;
	}

	/**
	 * get the vision of the intruder
	 * @return vision of the intruder
	 */
	public int getVision() {
		return vision;
	}

	/**
	 * set the vision of the intruder
	 * @param vision Number of vision
	 */
	public void setVision(int vision) {
		this.vision = vision;
	}

	/**
	 * get the agility of the intruder
	 * @return agility of the intruder
	 */
	public int getAgility() {
		return agility;
	}

	/**
	 * set the agility of the intruder
	 * @param agility Number of agility
	 */
	public void setAgility(int agility) {
		this.agility = agility;
	}

	/**
	 * get the list of items of the intruder
	 * @return list of items of the intruder
	 */
	public HashMap<String, Item> getItems() {
		return items;
	}

	/**
	 * set the list of items of the intruder
	 * @param items List of items
	 */
	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}
	
	/**
	 * set the vision zone of the intruder with his vision attribute in row and column
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
	 * get the vision zone of the intruder
	 * @return vision zone of the intruder
	 */
	public List<Block> getVisionzone() {
		return visionZone;
	}
	/**
	 * increment the agility given
	 * @param n The number
	 */
	public void incrementA(int n) {
		agility+=n;
	}
	/**
	 * decrement the agility given
	 * @param n The number
	 */
	public void decrementA(int n) {
		agility-=n;
	}
	/**
	 * increment the vision given
	 * @param n The number
	 */
	public void incrementV(int n) {
		vision+=n;
	}
	/**
	 * decrement the vision given
	 * @param n The number
	 */
	public void decrementV(int n) {
		vision-=n;
	}
	/**
	 * increment the precision given
	 * @param n The number
	 */
	public void incrementP(int n) {
		precision+=n;
	}
	/**
	 * decrement the precision given
	 * @param n The number
	 */
	public void decrementP(int n) {
		precision-=n;
	}
	/**
	 * increment the money earned
	 * @param n The number
	 */
	public void incrementM(int n) {
		moneyearned+=n;
	}
	/**
	 * decrement the money earned
	 * @param n The number
	 */
	public void decrementM(int n) {
		moneyearned-=n;
	}
	
	
	/**
	 * get the transparent count of the intruder
	 * @return the transparent count of the intruder
	 */
	public int getTransparentcount() {
		return transparentcount;
	}
	/**
	 * set the transparent count with the number given
	 * @param transparentcount Number of time the intruder became transparent
	 */
	public void setTransparentcount(int transparentcount) {
		this.transparentcount = transparentcount;
	}
	/**
	 * increment the transparent count 
	 */
	public void incrementIC() {
		transparentcount++;
	}
	
	/**
	 * decrement the transparent count 
	 */
	public void decrementIC() {
		transparentcount--;
	}
	/**
	 * pick a random number between a min and a max
	 * @param min The lower bound
	 * @param max The upper bound
	 * @return a random number
	 */
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}


}
