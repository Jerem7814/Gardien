package process;



import config.GameConfiguration;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Intruder;


/**
* this class contains essentially the same methods as GameBuilder but we separate them to distinguish them well. It allows to manage the initial elements of the game
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class GameBuilderGame {
	public static int nbrI=1;

	public static Map buildMap() {
		return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
	}

	public static MobileElementManagerGame buildInitMobile(Map map) {
		MobileElementManagerGame manager = new MobileElementManagerGame(map);
		
		intializeGate(map,manager);
		intializeIntruder(map, manager);
		
		return manager;
	}

	private static void intializeIntruder(Map map, MobileElementManagerGame manager) {
		Block block = map.getBlock(GameConfiguration.LINE_COUNT - 1, ((GameConfiguration.COLUMN_COUNT - 1) / 2)-1);
		Intruder intruder = new Intruder(block);
		intruder.inititem();
		manager.setIntruders(intruder);
	}
	
	private static void intializeGate(Map map, MobileElementManagerGame manager) {
		int rdm=getRandomNumber(0,GameConfiguration.COLUMN_COUNT-1);
		Block block = map.getBlock(0,rdm);
		ExitGate gate= new ExitGate(block);
		
		manager.setGate(gate);
	}
	


	
	
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
	

}
