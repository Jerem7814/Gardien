package process;

import java.util.ArrayList;


import config.GameConfiguration;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Intruder;
import model.Obstacle;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameBuilder {
	public static int nbrI=1;

	public static Map buildMap() {
		return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);
	}

	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);
		
		intializeGate(map,manager);
		intializeIntruder(map, manager);
	    //intializeObstacle(map,manager);
		
		return manager;
	}

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
	
	private static void intializeGate(Map map, MobileElementManager manager) {
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
