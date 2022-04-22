package map;

/**
* this class allows to initialize the map
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class Map {
	private Block[][] blocks;

	private int lineCount;
	private int columnCount;
	/**
	 * set the position on the map
	 * @param lineCount Line
	 * @param columnCount Column
	 */
	public Map(int lineCount, int columnCount) {
		this.lineCount = lineCount;
		this.columnCount = columnCount;

		blocks = new Block[lineCount][columnCount];

		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				blocks[lineIndex][columnIndex] = new Block(lineIndex, columnIndex);
			}
		}
	}
	/**
	 * get the blocks position
	 * @return blocks of position
	 */
	public Block[][] getBlocks() {
		return blocks;
	}
	/**
	 * get the line count
	 * @return the line count
	 */
	public int getLineCount() {
		return lineCount;
	}
	/**
	 * get the column count
	 * @return the column count
	 */
	public int getColumnCount() {
		return columnCount;
	}
	/**
	 * get block position with his coordinates 
	 * @param line in number
	 * @param column in number
	 * @return the line count
	 */
	public Block getBlock(int line, int column) {
		return blocks[line][column];
	}



}
