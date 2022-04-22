package map;
/**
 * this class allows to initialize the position of objects on the map
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class Block {
	private int line;
	private int column;
	
	/**
	 * set the position on the map with its coordinates
	 * @param line Line
	 * @param column Column
	 */
	public Block(int line, int column) {
		this.line = line;
		this.column = column;
	}


	/**
	 * get the line position
	 * @return line position
	 */
	public int getLine() {
		return line;
	}


	/**
	 * set the line position on the map
	 * @param line Line
	 */
	public void setLine(int line) {
		this.line = line;
	}


	/**
	 * get the column position
	 * @return column position
	 */
	public int getColumn() {
		return column;
	}


	/**
	 * set the column position on the map
	 * @param column Column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * check if the position is equal to the parameter
	 * @param position A block
	 * @return a boolean
	 */
	public boolean equals(Block position) {
		if(position.getColumn()==column&&position.getLine()==line) {
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * print the current line and the current column
	 * @return the current line and the column 
	 */
	@Override
	public String toString() {
		return "Block [line=" + line + ", column=" + column + "]";
	}

}
