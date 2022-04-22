package map;
public class Block {
	private int line;
	private int column;

	public Block(int line, int column) {
		this.line = line;
		this.column = column;
	}



	public int getLine() {
		return line;
	}



	public void setLine(int line) {
		this.line = line;
	}



	public int getColumn() {
		return column;
	}



	public void setColumn(int column) {
		this.column = column;
	}
	
	public boolean equals(Block position) {
		if(position.getColumn()==column&&position.getLine()==line) {
			return true;
		}
		else {
			return false;
		}
	}



	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}

}
