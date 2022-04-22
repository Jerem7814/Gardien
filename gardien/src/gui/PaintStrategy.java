package gui;

import java.awt.Color;




import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import config.GameConfiguration;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Guardian;
import model.Item;
import model.Intruder;

/**
* initialize the dashboard with it's tiles, intruders, guardians and items
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class PaintStrategy {

	
	/**
	 * create Blocks so we can place entities on it
	 * @param map
	 * @param graphics
	 */
	public void paint(Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;

		Block[][] blocks = map.getBlocks();
		

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];

				if ((lineIndex + columnIndex) % 2 == 0) {
					graphics.setColor(Color.GRAY);
					graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				}
			}
		}
	}
	
	
	
	/**
	 * Place the intruder(s) on the map with Line and Collumn of the map
	 * @param intruders An arrayList of thiefthat
	 * @param graphics
	 */
	public void paintI(List<Intruder> intruders, Graphics graphics)  {
		for(Intruder intruder : intruders) {
			Block position = intruder.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();
			if(intruder.isTransparent()) {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/thiefinvisible.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			}
			else {
	
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/thief.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}

	}

	/**
	 * Place the guardianon the map with Line and Collumn of the map
	 * @param guardians
	 * @param graphics
	 */
	public void paintG(List<Guardian> guardians, Graphics graphics)  {
		for(Guardian guardian : guardians) {
			Block position = guardian.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();
	
			try {
				graphics.drawImage(ImageIO.read(new File("ressources/images/gardien.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	
	/**
	 * Place the gate on the map with Line and Collumn of the map
	 * @param gate
	 * @param graphics
	 */
	public void paintGate(ExitGate gate, Graphics graphics)  {
		
		Block position = gate.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;
	
		int y = position.getLine();
		int x = position.getColumn();
	
		try {
			graphics.drawImage(ImageIO.read(new File("ressources/images/gate.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Place the items on the map with Line and Collumn of the map
	 * @param items
	 * @param graphics
	 */
	public void paintIt(List<Item> items, Graphics graphics)  {

		for(Item item : items) {
			Block position = item.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;

			int y = position.getLine();
			int x = position.getColumn();
			if(item.getName() == "Filet") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/net.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(item.getName() == "Lure") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/speaker.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(item.getName() == "Invisibility cloak") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/cloak.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(item.getName() == "Agility Potion") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/agility.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(item.getName() == "Vision Potion") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/vision.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(item.getName() == "Precision Potion") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/precision.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(item.getName() == "Money") {
				try {
					graphics.drawImage(ImageIO.read(new File("ressources/images/money.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			

		
		}
	}
	
	/**
	 * Place the lure on the map with Line and Collumn of the map
	 * @param lure
	 * @param graphics
	 */
	public void paintMLure(Item lure, Graphics graphics)  {
			Block position = lure.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();

			try {
				graphics.drawImage(ImageIO.read(new File("ressources/images/mobilelure.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		/**
		 * Place the filet on the map with Line and Collumn of the map
		 * @param filet
		 * @param graphics
		 */
		public void paintMFilet(Item filet, Graphics graphics)  {
			Block position = filet.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;

			int y = position.getLine();
			int x = position.getColumn();

			try {
				graphics.drawImage(ImageIO.read(new File("ressources/images/mobilenet.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}


			
			

		}
		
	




}
