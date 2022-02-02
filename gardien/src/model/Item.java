package model;

import java.awt.*;

import map.Block;


public class Item extends Element{
	private int nbre;
	private int priority;
	private String name;
	private Image image;
	


	public Item(Block position, int nbre, int priority, String name, Image image) {
		super(position);
		this.nbre = nbre;
		this.priority = priority;
		this.name = name;
		this.image = image;
	}

	public int getNbre() {
		return nbre;
	}

	public void setNbre(int nbre) {
		this.nbre = nbre;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
