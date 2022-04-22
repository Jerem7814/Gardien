package model;

import map.Block;

public abstract class MElement {
	private Block position;

	public MElement(Block position) {
		this.position = position;
	}

	public Block getPosition() {
		return position;
	}

	public void setPosition(Block position) {
		this.position = position;
	}

}
