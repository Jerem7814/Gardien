package gui;

import java.awt.Graphics;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import config.GameConfiguration;
import map.Block;
import map.Map;
import model.Guardian;
import model.Item;
import model.Intruder;
import process.MobileElementManagerGame;

/**
 * make the connection between manager and PaintStrategyGame
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class GameDisplayGame extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map map;
	private MobileElementManagerGame manager;
	private PaintStrategyGame paintStrategy = new PaintStrategyGame();

	public GameDisplayGame(Map map, MobileElementManagerGame manager) {
		this.map = map;
		this.manager = manager;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintStrategy.paint(map, g);
		


		
		
		paintStrategy.paintGate(manager.getGate(),g );
		
		
		List<Item> items = manager.getItems();
		List<Item> itemscopy = new ArrayList<Item>(items); //copies the content

		paintStrategy.paintIt(itemscopy, g);
		
		Intruder intruders = manager.getIntruders();
		List<Intruder> intruderscopy = new ArrayList<Intruder>(); //copies the content
		if(intruders!=null) {
			intruderscopy.add(intruders);
		}
		
		
		paintStrategy.paintI(intruderscopy, g);
		List<Guardian> guardians = manager.getGuardians();
		List<Guardian> guardianscopy = new ArrayList<Guardian>(guardians); //copies the content

		paintStrategy.paintG(guardianscopy, g);
		

		


		



		

		
		
		Item mobilelure = manager.getMlure();
		if(mobilelure!=null) {
			paintStrategy.paintMLure(mobilelure, g);
			
		}
		

		if(!manager.intrudervoid()) {
			List <Block> vision = manager.getIntruders().getVisionzone();
			List<Block> visioncopy = new ArrayList<Block>(vision); //copies the content	
			paintStrategy.paintblack(manager.getIntruders(),visioncopy,map, g);
		}


		
	}
	public Block getIntruderPosition(int x, int y) {
		int line = y / GameConfiguration.BLOCK_SIZE;
		int column = x / GameConfiguration.BLOCK_SIZE;
		return map.getBlock(line, column);
	}



}

