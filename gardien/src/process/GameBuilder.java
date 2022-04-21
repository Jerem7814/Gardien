package process;

import java.util.ArrayList;



import config.GameConfiguration;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Intruder;

/**
* initialize the elements present from the start of the simulation
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class GameBuilder {
	public static int nbrI=1;

	public static Map buildMap() {
		return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
	}

	/**
	 * build the elements present from the start of the game 
	 * @param map The map where elements will appear
	 * @return the map with initialized elements
	 */
	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);
		
		intializeGate(map,manager);
		intializeIntruder(map, manager);
		
		return manager;
	}

	/**
	 * initialize intruders on the map 
	 * @param map The map where intruders will appear
	 * @param manager We will add intruder in the list of manager to make them mobile
	 */
	private static void intializeIntruder(Map map, MobileElementManager manager) {
		ArrayList<Intruder> intruders=new ArrayList<Intruder>();
		int m=0;
		
		for(int i=0;i<nbrI;i++) {
			if(i%2==0) {
				Block block = map.getBlock(GameConfiguration.LINE_COUNT - 1, (((GameConfiguration.COLUMN_COUNT - 1) / 2))-m);
				Intruder intruder = IntruderPositionFactory.createIntruder(block);
				intruder.inititem();
				intruders.add(intruder);
				m+=2;
			}
			else {
				Block block = map.getBlock(GameConfiguration.LINE_COUNT - 1, (((GameConfiguration.COLUMN_COUNT - 1) / 2))+m);
				Intruder intruder = IntruderPositionFactory.createIntruder(block);
				intruder.inititem();
				intruders.add(intruder);
			}
		}
		manager.setIntruders(intruders);
	}
	
	/**
	 * initialize the exit gate on the map 
	 * @param map The map where intruders will appear
	 * @param manager We will add the gate in the manager to be able to handle it with mobile elements 
	 */
	private static void intializeGate(Map map, MobileElementManager manager) {
		int rdm=getRandomNumber(0,GameConfiguration.COLUMN_COUNT-1);
		Block block = map.getBlock(0,rdm);
		ExitGate gate= new ExitGate(block);
		
		manager.setGate(gate);
	}
	


	
	 /**
	 * pick a random number between a min and a max
	 * @param min The lower bound
	 * @param max The upper bound
	 * @return a random number
	 */
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

}
