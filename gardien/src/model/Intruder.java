package model;

import java.util.HashMap;

import map.Block;

public class Intruder extends Element{
	private int vision;
	private int agility;
	private HashMap<String, Item> items;
	public int dodge;
	public int moneyearned;



	
	public Intruder(Block position, int vision, int agility, HashMap<String, Item> items, int dodge, int moneyearned) {
		super(position);
		this.vision = vision;
		this.agility = agility;
		this.items = items;
		this.dodge = dodge;
		this.moneyearned = moneyearned;
	}
	
	public Intruder(Block position) {
		super(position);
		this.vision = 0;
		this.agility = 0;
		items = new HashMap<String,Item>();
		this.dodge = 0;
		this.moneyearned = 0;
	}

	public void inititem() {
		String pA="Agility Potion";
		int ppA=4;
		int nbpA=0;
		Item ap=new Item(null,nbpA,ppA,pA,null);
		String pV="Vision Potion";
		int ppV=6;
		int nbpV=0;
		Item vp=new Item(null,nbpV,ppV,pV,null);
		String pR="Reflex Potion";
		int ppR=5;
		int nbpR=0;
		Item rp=new Item(null,nbpR,ppR,pR,null);
		String iC="Invisibility cloak";
		int pIc=2;
		int nbiC=0;
		Item invisible=new Item(null,nbiC,pIc,iC,null);
		String lure="Lure";
		int pLure=3;
		int nbl=0;
		Item l=new Item(null,nbl,pLure,lure,null);
		String money="Money";
		int pMoney=1;
		int nbM=0;
		Item dollars=new Item(null,nbM,pMoney,money,null);
		items.put(lure, l);
		items.put(iC, invisible);
		items.put(pR, rp);
		items.put(pV, vp);
		items.put(pA, ap);
		items.put(money, dollars);
		moneyearned=0;
		vision=2;
		agility=2;
		dodge=3;
	}
	
	public int getDodge() {
		return dodge;
	}


	public void setDodge(int dodge) {
		this.dodge = dodge;
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


}
