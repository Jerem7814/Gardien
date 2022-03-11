package gui;

import java.awt.Color;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import config.GameConfiguration;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Guardian;
import model.Item;
import model.Intruder;
import process.MobileElementManager;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class PaintStrategy {
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

	public void paintI(List<Intruder> intruders, Graphics graphics) throws IOException {
		for(Intruder intruder : intruders) {
			Block position = intruder.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();
	
			graphics.drawImage(ImageIO.read(new File("ressources/images/thief.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);


		}

	}

	public void paintG(List<Guardian> guardians, Graphics graphics) throws IOException {
		for(Guardian guardian : guardians) {
			Block position = guardian.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();
	
			graphics.drawImage(ImageIO.read(new File("ressources/images/gardien.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
		}
	}
	
	public void paintGate(ExitGate gate, Graphics graphics) throws IOException {
		
		Block position = gate.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;
	
		int y = position.getLine();
		int x = position.getColumn();
	
		graphics.drawImage(ImageIO.read(new File("ressources/images/gate.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
		
	}

	public void paintIt(List<Item> items, Graphics graphics) throws IOException {
		for(Item item : items) {
			Block position = item.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();
			if(item.getName() == "Filet") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/net.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			}
			else if(item.getName() == "Lure") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/speaker.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);

			}
			else if(item.getName() == "Invisibility cloak") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/cloak.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);

			}
			else if(item.getName() == "Agility Potion") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/agility.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);

			}
			else if(item.getName() == "Vision Potion") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/vision.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			}
			else if(item.getName() == "Precision Potion") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/precision.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			}
			else if(item.getName() == "Money") {
				graphics.drawImage(ImageIO.read(new File("ressources/images/money.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);
			}
			


		}
	}
	
	public void paintMLure(Item lure, Graphics graphics) throws IOException {
			Block position = lure.getPosition();
			int blockSize = GameConfiguration.BLOCK_SIZE;
	
			int y = position.getLine();
			int x = position.getColumn();

			graphics.drawImage(ImageIO.read(new File("ressources/images/mobilelure.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);


			
			


		
	}
	public void paintMFilet(Item filet, Graphics graphics) throws IOException {
		Block position = filet.getPosition();
		int blockSize = GameConfiguration.BLOCK_SIZE;

		int y = position.getLine();
		int x = position.getColumn();

		graphics.drawImage(ImageIO.read(new File("ressources/images/mobilenet.png")),x * blockSize,y * blockSize,blockSize, blockSize,null);


		
		


	
}



}
