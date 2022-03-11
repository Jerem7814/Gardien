package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import map.Block;

public class Intruder extends MElement{
	private int vision;
	private int agility;
	private HashMap<String, Item> items;
	public int precision;
	public int moneyearned;
	private List<Block> visionZone;



	
	public Intruder(Block position, int vision, int agility, HashMap<String, Item> items, int dodge, int moneyearned) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		this.items = items;
		this.precision = dodge;
		this.moneyearned = moneyearned;
	}
	
	public Intruder(Block position) {
		super(position);
		this.vision = 0;
		this.agility = 0;
		items = new HashMap<String,Item>();
		this.precision = 0;
		this.moneyearned = 0;
		visionZone = new ArrayList<Block>();
	}

	public void inititem() {
		String pA="Agility Potion";
		int nbpA=0;
		Item ap=new Item(null,nbpA,pA,null);
		String pV="Vision Potion";
		int nbpV=0;
		Item vp=new Item(null,nbpV,pV,null);
		String pR="Precision Potion";
		int nbpR=0;
		Item rp=new Item(null,nbpR,pR,null);
		String iC="Invisibility cloak";
		int nbiC=0;
		Item invisible=new Item(null,nbiC,iC,null);
		String lure="Lure";
		int nbl=1;
		Item l=new Item(null,nbl,lure,null);
		String money="Money";
		int nbM=0;
		Item dollars=new Item(null,nbM,money,null);
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
		setVisionzone();
	}
	
	public int getDodge() {
		return precision;
	}


	public void setDodge(int dodge) {
		this.precision = dodge;
	}


	public int getMoneyearned() {
		return moneyearned;
	}


	public void setMoneyearned(int moneyearned) {
		this.moneyearned = moneyearned;
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
	
	public List<Block> getVisionzone() {
		return visionZone;
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
	
	public void incrementM(int n) {
		moneyearned+=n;
	}
	
	public void decrementM(int n) {
		moneyearned-=n;
	}
	
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}


}
