package gui;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import config.GameConfiguration;
import map.Block;
import map.Map;
import model.Guardian;
import model.Item;
import model.MobileItem;
import model.Obstacle;
import model.Intruder;
import process.MobileElementManager;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map map;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	public static boolean stop = true;

	public GameDisplay(Map map, MobileElementManager manager) {
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
		

		List<Guardian> guardians = manager.getGuardians();
		List<Guardian> guardianscopy = new ArrayList<Guardian>(guardians); //copies the content

		paintStrategy.paintG(guardianscopy, g);
		

		

		List<Intruder> intruders = manager.getIntruders();
		List<Intruder> intruderscopy = new ArrayList<Intruder>(intruders); //copies the content

		paintStrategy.paintI(intruderscopy, g);
		


		List<Obstacle> obstacles = manager.getObstacles();
		List<Obstacle> obstaclescopy = new ArrayList<Obstacle>(obstacles); //copies the content

		paintStrategy.paintO(obstaclescopy, g);
		

		
		
		MobileItem mobilelure = manager.getMlure();
		if(mobilelure!=null) {
			paintStrategy.paintMLure(mobilelure, g);
			
		}
		
		MobileItem mobilenet = manager.getMfilet();
		if(mobilenet!=null) {
			paintStrategy.paintMFilet(mobilenet, g);
			
		}
		
	}



}
