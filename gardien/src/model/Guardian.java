package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import map.Block;

public class Guardian extends MElement{
	private int vision;
	private int agility;
	private HashMap<String, Item> items;
	private int precision;
	private List<Block> visionZone;
	

	
	
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
	
	public Guardian(Block position) {
		super(position);
		this.vision = 0;
		this.agility = 0;
		items = new HashMap<String,Item>();
		this.precision = 0;
		visionZone = new ArrayList<Block>();
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
		String pP="Precision Potion";
		int ppP=4;
		int nbpP=0;
		Item pp=new Item(null,nbpP,ppP,pP,null);
		String filet="Filet";
		int pFilet=1;
		int nbF=2;
		Item fil=new Item(null,nbF,pFilet,filet,null);
		items.put(filet, fil);
		items.put(pP, pp);
		items.put(pV, vp);
		items.put(pA, ap);	
		vision=1;
		agility=1;
		precision=5;
		setVisionzone();
	}

	
	public void setVisionzone() {
		int line = super.getPosition().getLine();
		int column = super.getPosition().getColumn();
		for(int i=1;i<=vision;i++) {
			Block bas=new Block(line+i,column);
			Block haut=new Block(line-i,column);
			Block gauche=new Block(line,column-i);
			Block droite=new Block(line,column+i);
	
			if(line-i>0) {
				visionZone.add(haut);
			}
			if(line+i<GameConfiguration.LINE_COUNT) {
				visionZone.add(bas);
			}
			if(column-i>0) {
				visionZone.add(gauche);
			}
			if(column+i<GameConfiguration.COLUMN_COUNT) {
				visionZone.add(droite);
			}
		}
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

	
	
	public List<Block> getVisionzone() {
		return visionZone;
	}

	public int getPrecision() {
		return precision;
	}


	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public void incrementA(int n) {
		agility+=n;
	}
	
	public void decrementA(int n) {
		agility-=n;
	}
	
	public void incrementV(int n) {
		vision+=n;
	}
	
	public void decrementV(int n) {
		vision-=n;
	}
	
	public void incrementP(int n) {
		precision+=n;
	}
	
	public void decrementP(int n) {
		precision-=n;
	}
	


	

}
