package process;

import java.util.ArrayList;

import config.GameConfiguration;
import gui.OpenGame;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Intruder;
import model.Obstacle;

public class GameBuilderGame {
	public static int nbrI=1;

	public static Map buildMap() {
		return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
	}

	public static MobileElementManagerGame buildInitMobile(Map map) {
		MobileElementManagerGame manager = new MobileElementManagerGame(map);
		
		intializeGate(map,manager);
		intializeIntruder(map, manager);
	    //intializeObstacle(map,manager);
		
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
	
	private static void intializeObstacle(Map map, MobileElementManager manager) {
		boolean droppedobstacle = false;
		ArrayList<Obstacle> obstacles=new ArrayList<Obstacle>();
		int index=0;
		while(index<GameConfiguration.LINE_COUNT/3) {
				Block block = map.getBlock(getRandomNumber(1,GameConfiguration.LINE_COUNT-2),getRandomNumber(0,GameConfiguration.LINE_COUNT-2));
				Block block2 = map.getBlock(block.getLine(),block.getColumn()+1);
				Obstacle ob1=new Obstacle(block);
				Obstacle ob2=new Obstacle(block2);
				obstacles.add(ob1);
				obstacles.add(ob2);
				droppedobstacle=true;
				index+=1;
	
			
		}
		manager.setObstacles(obstacles);
	}

	
	
	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
	

}
