package model;
import java.awt.*;

import map.Block;

public class Obstacle extends MElement{
	private Image image;

	public Obstacle(Block position, Image image) {
		super(position);
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	

	
	

}
