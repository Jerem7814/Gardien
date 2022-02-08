package process;

import map.Map;
import model.Guardian;
import model.Intruder;
import model.Item;
import model.MElement;
import map.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import engine.mobile.Enemy;
import engine.mobile.Missile;




/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class MobileElementManager {
	private Map map;
	int round;
	private List<Item> items = new ArrayList<Item>();
	private List<Guardian> guardians = new ArrayList<Guardian>();
	private List<Intruder> intruders = new ArrayList<Intruder>();
	

	public MobileElementManager(Map map) {
		this.map = map;
		this.round=0;
	}

	public void add(Item item) {
		items.add(item);
	}

	public void add(Guardian guardian) {
		guardians.add(guardian);
	}

	public void add(Intruder intruder) {
		intruders.add(intruder);
	}








	public void nextRound() {
		incrementRound();
		if(isTen()) {
			generateGuardian();
		}
		if(isFive()) {
			generateItem();
		}
		evolution();
		combat();
		
	}
	
	public boolean isTen() {
		if(round%10==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isFive() {
		if(round%5==0) {
			return true;
		}
		else {
			return false;
		}
	}

	private void generateGuardian() {
		int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
		Block position = new Block(0, randomColumn);
		Guardian guardian = new Guardian(position);
		guardian.inititem();
		add(guardian);
	}
	
	
	private void generateItem() {
		int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
		int lineColumn = getRandomNumber(0, GameConfiguration.LINE_COUNT - 1);
		Block position = new Block(lineColumn, randomColumn);
		String nm=randomItem();
		Item item = new Item(position,nm);
		add(item);	
		}
	
	public String randomItem() {
		HashMap<String,String> rdm = new HashMap<String,String>();
		rdm.put("0", "Agility Potion");
		rdm.put("1", "Vision Potion");
		rdm.put("2", "Precision Potion");
		rdm.put("3", "Dodge Potion");
		rdm.put("4", "Filet");
		rdm.put("5", "Lure");
		rdm.put("6", "Invisibility cloak");
		int r=getRandomNumber(0,6);
		String nb=String.valueOf(r);
		String rt=rdm.get(nb);
		return rt;
	}
	
	public void incrementRound() {
		round+=1;
	}





	private void winnerG() {
		List<Intruder> eliminatedIntruder = new ArrayList<Intruder>();
		for (Guardian guardian : guardians) {
			Block guardianPosition = guardian.getPosition();
			for (Intruder intruder : intruders) {
				if (intruder.getPosition().equals(guardianPosition)) {
					eliminatedIntruder.add(intruder);
				}
			}
		}
		for (Intruder intruder : eliminatedIntruder) {
			intruders.remove(intruder);
		}
	}
	

	
	private void winnerI() {
		
	}
	
	public void evolution() {
		evolutionI();
		evolutionG();
	}
	
	public void evolutionG() {
			List<Item> eliminatedItem = new ArrayList<Item>();
			for (Guardian guardian : guardians) {
				Block guardianPosition = guardian.getPosition();
				for (Item item : items) {
					if (item.getPosition().equals(guardianPosition)) {
						Item i=item;
						eliminatedItem.add(item);
						if(i.getName() == "Filet") {
							HashMap<String,Item> upgrade=guardian.getItems();
							Item m=upgrade.get("Filet");
							if(m.getNbre() <= 3) {
								m.increment(1);
							}
							upgrade.put("Filet", m);
							guardian.setItems(upgrade);
						}
						
						else if(i.getName() == "Agility Potion") {
							if(guardian.getAgility() <= 10) {
								guardian.incrementA(1);
							}
						}
						
						else if(i.getName() == "Vision Potion") {
							if(guardian.getVision() <= 10) {
								guardian.incrementV(1);
								guardian.setVisionzone();
							}
						}
						
						else if(i.getName() == "Precision Potion") {
							if(guardian.getPrecision() <= 10) {
								guardian.incrementP(1);
							}
						}
					}
					}
				}
			}
	
	public void combat() {
		for (Guardian guardian : guardians) {
			Block guardianPosition = guardian.getPosition();
			for (Intruder intruder : intruders) {
				if (intruder.getPosition().equals(guardianPosition)) {
					if(guardian.getAgility()<intruder.getDodge()) {
						winnerI();
					}
					else {
						winnerG();
					}
				}
				}
			}
	}
	
	public void evolutionI() {
		List<Item> eliminatedItem = new ArrayList<Item>();
		for (Intruder intruder : intruders) {
			Block intruderPosition = intruder.getPosition();
			for (Item item : items) {
				if (item.getPosition().equals(intruderPosition)) {
					Item i=item;
					eliminatedItem.add(item);
					if(i.getName() == "Invisibility cloak") {
						HashMap<String,Item> upgrade=intruder.getItems();
						Item m=upgrade.get("Invisibility cloak");
						if(m.getNbre() <= 3) {
							m.increment(1);
						}
						upgrade.put("Invisibility cloak", m);
						intruder.setItems(upgrade);
					}
					
					else if(i.getName() == "Lure") {
						HashMap<String,Item> upgrade=intruder.getItems();
						Item m=upgrade.get("Lure");
						if(m.getNbre() <= 3) {
							m.increment(1);
						}
						upgrade.put("Lure", m);
						intruder.setItems(upgrade);
					}
					
					
					else if(i.getName() == "Money") {
						intruder.incrementM(100);
					}
					
					else if(i.getName() == "Agility Potion") {
						if(intruder.getAgility() <= 10) {
							intruder.incrementA(1);
						}
					}
					
					else if(i.getName() == "Vision Potion") {
						if(intruder.getVision() <= 10) {
							intruder.incrementV(1);
							intruder.setVisionzone();
						}
					}
					
					else if(i.getName() == "Dodge Potion") {
						if(intruder.getDodge() <= 10) {
							intruder.incrementD(1);
						}
					}
				}
				}
			}
		}
	

			
			
	

	
	public void deplapriorité(Guardian a) {
		
	}
	
	
	public void deplaléatoire(Guardian a) {
		
		
		int rdm=getRandomNumber(0,3);
		
	

		if(rdm==0) {
			a.getPosition().setColumn(a.getPosition().getColumn()+1);
		}
		else if(rdm==1) {
			a.getPosition().setColumn(a.getPosition().getColumn()-1);
		}
		
		else if(rdm==2) {
			a.getPosition().setLine(a.getPosition().getLine()+1);
		}
		else if(rdm==0) {
			a.getPosition().setLine(a.getPosition().getLine()-1);
		}
		
	}
	

	

	



	



	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	public List<Intruder> getIntruders() {
		return intruders;
	}

	public void setIntruders(List<Intruder> intruders) {
		this.intruders = intruders;
	}





	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
}
