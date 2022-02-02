package model;

import java.util.HashMap;

import map.Block;

public class Guardian extends Element{
	private int vision;
	private int agility;
	private HashMap<String, Item> items;
	private int precision;

	

	
	
	public Guardian(Block position, int vision, int agility, HashMap<String, Item> items, int precision) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		this.items = items;
		this.precision = precision;
	}
	
	
	public Guardian(Block position, int vision, int agility, int precision) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		items = new HashMap<String,Item>();
		this.precision = precision;
	}


	public void inititem() {
		String pA="Agility Potion";
		int ppA=3;
		int nbpA=0;
		Item ap=new Item(null,nbpA,ppA,pA,null);
		String pV="Vision Potion";
		int ppV=5;
		int nbpV=0;
		Item vp=new Item(null,nbpV,ppV,pV,null);
		String pR="Reflex Potion";
		int ppR=4;
		int nbpR=0;
		Item rp=new Item(null,nbpR,ppR,pR,null);
		String filet="Filet";
		int pFilet=1;
		int nbF=2;
		Item fil=new Item(null,nbF,pFilet,filet,null);
		items.put(filet, fil);
		items.put(pR, rp);
		items.put(pV, vp);
		items.put(pA, ap);	
		vision=1;
		agility=1;
		precision=4;
	}


	public int getVision() {
		return vision;
	}


	public void setVision(int vision) {
		this.vision = vision;
	}


	public int getAgility() {
		return agility;
	}


	public void setAgility(int agility) {
		this.agility = agility;
	}


	public HashMap<String, Item> getItems() {
		return items;
	}


	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}


	public int getPrecision() {
		return precision;
	}


	public void setPrecision(int precision) {
		this.precision = precision;
	}


	

}
