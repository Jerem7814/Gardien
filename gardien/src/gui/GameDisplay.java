package gui;

import java.awt.Graphics;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


import map.Map;
import model.Guardian;
import model.Item;
import model.Intruder;
import process.MobileElementManager;

/**
* initialize the dashboard with its components for the simulation
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class GameDisplay extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map map;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	public static boolean stop = true;
	
	/**
	 * 
	 * @param map
	 * @param manager
	 */
	public GameDisplay(Map map, MobileElementManager manager) {
		this.map = map;
		this.manager = manager;
	}

	@Override
	/**intialize the position of the entities on the map
	 * @param g
	 */
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
		

		Item mobilenet = manager.getMfilet();
		if(mobilenet!=null) {
			paintStrategy.paintMFilet(mobilenet, g);
			
		}

		List<Intruder> intruders = manager.getIntruders();
		List<Intruder> intruderscopy = new ArrayList<Intruder>(intruders); //copies the content

		paintStrategy.paintI(intruderscopy, g);
		



		

		
		
		Item mobilelure = manager.getMlure();
		if(mobilelure!=null) {
			paintStrategy.paintMLure(mobilelure, g);
			
		}

		
	}



}
