package model;

import java.awt.*;

import map.Block;


public class Item extends MElement{
	private int nbre;
	private String name;
	


	public Item(Block position, int nbre, String name, Image image) {
		super(position);
		this.nbre = nbre;
		this.name = name;
	}
	
	public Item(Block position, String name) {
		super(position);
		this.nbre = 0;
		this.name = name;
	}

	public int getNbre() {
		return nbre;
	}

	public void setNbre(int nbre) {
		this.nbre = nbre;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void increment(int n) {
		nbre+=n;
	}
	
	public void decrement(int n) {
		nbre-=n;
	}




	
	
}
