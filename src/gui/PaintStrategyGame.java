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
 * Allows the display of different objects, characters and the map
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class PaintStrategyGame {
	
	public File Gimage=new File("ressources/images/thiefinvisible.png");
	
	
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

	public void paintblack(Intruder i, List<Block> vision,Map map, Graphics graphics) {
		int blockSize = GameConfiguration.BLOCK_SIZE;


		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				
				Block block = new Block(lineIndex,columnIndex);




				if(!isIn(vision,block)&&!block.equals(i.getPosition())) {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize, blockSize);
				}
			}
		}
		

	}

	

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



		
		


	

	
	public boolean isIn(List<Block> vision, Block block) {
		for(Block blok:vision) {
			if(blok.equals(block)) {
				return true;
			}
		}
		return false;
	}
	



}
