package process;

import map.Map;
import model.ExitGate;
import model.Guardian;
import model.Intruder;
import model.Item;
import sound.Sound;
import map.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;




/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class MobileElementManager {
	private Map map;
	int round;
	private boolean iHasmoved=false;
	private List<Item> items = new ArrayList<Item>();
	private List<Guardian> guardians = new ArrayList<Guardian>();
	private List<Intruder> intruders = new ArrayList<Intruder>();
	private ExitGate gate;
	private List<Intruder> freeintruders = new ArrayList<Intruder>();

	

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
		exitintruders();
		if(isNumber(75)) {
			generateGuardian();
		}
		if(isNumber(50)) {
			generateItem();
		}
		if(isNumber(25)) {
			generateMoney();
		}
		if(isNumber(2)) {
			iSEEg();
		}
		else {
		
			deplaleatoireG();
		}
		evolution();
		combat();
		incrementRound();



	}
	
	public boolean isNumber(int i) {
		if(round%i==0) {
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
	
	private void generateMoney() {
		int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
		int lineColumn = getRandomNumber(0, GameConfiguration.LINE_COUNT - 1);
		Block position = new Block(lineColumn, randomColumn);
		String nm="Money";
		Item item = new Item(position,nm);
		add(item);	
		}
	
	public String randomItem() {
		HashMap<String,String> rdm = new HashMap<String,String>();
		rdm.put("0", "Agility Potion");
		rdm.put("1", "Vision Potion");
		rdm.put("2", "Precision Potion");
		rdm.put("6", "Precision Potion");
		rdm.put("7", "Precision Potion");
		rdm.put("10", "Precision Potion");
		rdm.put("11", "Filet");
		rdm.put("9", "Filet");
		rdm.put("3", "Filet");
		rdm.put("12", "Lure");
		rdm.put("8", "Lure");
		rdm.put("4", "Lure");
		rdm.put("5", "Invisibility cloak");
		int r=getRandomNumber(0,12);
		String nb=String.valueOf(r);
		String rt=rdm.get(nb);
		return rt;
	}
	
	public void incrementRound() {
		round+=1;
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
						Sound s=new Sound();
						System.out.println("Item gagne");

						Item i=item;
						eliminatedItem.add(item);
						if(i.getName() == "Filet") {
							s.setFile(4);
							s.play();
							HashMap<String,Item> upgrade=guardian.getItems();
							Item m=upgrade.get("Filet");
							if(m.getNbre() <= 3) {
								m.increment(1);
							}
							upgrade.put("Filet", m);
							guardian.setItems(upgrade);
						}
						
						else if(i.getName() == "Agility Potion") {
							s.setFile(1);
							s.play();
							if(guardian.getAgility() <= 3) {
								guardian.incrementA(1);
							}
						}
						
						else if(i.getName() == "Vision Potion") {
							s.setFile(1);
							s.play();
							if(guardian.getVision() <= 3) {
								guardian.incrementV(1);
								guardian.setVisionzone();
							}
						}
						
						else if(i.getName() == "Precision Potion") {
							s.setFile(1);
							s.play();
							if(guardian.getPrecision() <= 15) {
								guardian.incrementP(1);
							}
						}
						else {
							s.setFile(6);
							s.play();
						}
					}
					}
				}
			for(Item item:eliminatedItem) {
				items.remove(item);
			}			}
	

	
	public void evolutionI() {
		List<Item> eliminatedItem = new ArrayList<Item>();
		for (Intruder intruder : intruders) {
			Block intruderPosition = intruder.getPosition();
			for (Item item : items) {
				if (item.getPosition().equals(intruderPosition)) {
					Sound s = new Sound();
					Item i=item;
					eliminatedItem.add(item);
					if(i.getName() == "Invisibility cloak") {
						s.setFile(4);
						s.play();
						HashMap<String,Item> upgrade=intruder.getItems();
						Item m=upgrade.get("Invisibility cloak");
						if(m.getNbre() <= 3) {
							m.increment(1);
						}
						upgrade.put("Invisibility cloak", m);
						intruder.setItems(upgrade);
					}
					
					else if(i.getName() == "Lure") {
						s.setFile(4);
						s.play();
						HashMap<String,Item> upgrade=intruder.getItems();
						Item m=upgrade.get("Lure");
						if(m.getNbre() <= 3) {
							m.increment(1);
						}
						upgrade.put("Lure", m);
						intruder.setItems(upgrade);
					}
					
					
					else if(i.getName() == "Money") {
						s.setFile(0);
						s.play();
						intruder.incrementM(100);
					}
					
					else if(i.getName() == "Agility Potion") {
						s.setFile(1);
						s.play();
						if(intruder.getAgility() <= 3) {
							intruder.incrementA(1);
							intruder.incrementV(1);
						}
					}
					
					else if(i.getName() == "Vision Potion") {
						s.setFile(1);
						s.play();
						if(intruder.getVision() <= 3) {
							intruder.incrementV(1);
							intruder.setVisionzone();
						}
					}
					
					else if(i.getName() == "Precision Potion") {
						s.setFile(1);
						s.play();
						if(intruder.getDodge() <= 15) {
							intruder.incrementP(1);
						}
					}
					else {
						s.setFile(3);
						s.play();
					}
				}
				}
			}
		for(Item item:eliminatedItem) {
			items.remove(item);
		}	
		}
	

			
	public void combat() {
		List<Intruder> eliminatedIntruder = new ArrayList<Intruder>();
		List<Guardian> eliminatedGuardian = new ArrayList<Guardian>();
		for (Guardian guardian : guardians) {
			Block guardianPosition = guardian.getPosition();
			for (Intruder intruder : intruders) {

				if (intruder.getPosition().equals(guardianPosition)) {
					Sound s=new Sound();
					if(guardian.getPrecision()<intruder.getDodge()) {
						s.setFile(3);
						s.play();
						intruder.decrementP(1);
						eliminatedGuardian.add(guardian);
					}
					else {
						s.setFile(2);
						s.play();
						guardian.decrementP(1);
						eliminatedIntruder.add(intruder);
					}
				}
				}
			}
		for(Guardian guardian:eliminatedGuardian) {
			guardians.remove(guardian);
		}	
		for(Intruder intruder:eliminatedIntruder) {
			intruders.remove(intruder);
		}
	}	
	
	
	public void iSEEg(){
		for(Intruder a : intruders) {
			a.setVisionzone();
			boolean haverunaway=false;
			List<Block> ivision=a.getVisionzone();
			for(Block b:ivision) {
				for(Guardian g:guardians) {
					if(g.getPosition().equals(b)) {

						runaway(a,g);
						haverunaway=true;
						break;
					}
				}
				if(haverunaway) {
					break;
				}
			}
			if(!haverunaway) {
				deplapriorite(a);
			}
		}
		
	}
	
	public void runaway(Intruder i,Guardian g) {

		Block guardianPos=g.getPosition();
		Block intruderPosition=i.getPosition();
		int lineG=guardianPos.getLine();
		int columnG=guardianPos.getColumn();
		int lineI=intruderPosition.getLine();
		int columnI=intruderPosition.getColumn();
		iHasmoved=false;
		if((lineI==lineG) && (columnG<columnI)) {
			System.out.println("avant"+intruderPosition);

			deplacement(i,0,i.getAgility());
			System.out.println("apres qui doit etre avant"+intruderPosition);
			System.out.println("apres"+i.getPosition());
			if(!iHasmoved) {
				deplacement(i,3,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,2,i.getAgility());
			}

		}
		else if((lineI==lineG) && (columnG>columnI)) {
			System.out.println("avant"+intruderPosition);

			deplacement(i,1,i.getAgility());
			System.out.println("apres qui doit etre avant"+intruderPosition);
			System.out.println("apres"+i.getPosition());


			if(!iHasmoved) {
				deplacement(i,3,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,2,i.getAgility());
			}

		}
		else if((lineI<lineG) && (columnG==columnI)) {
			System.out.println("avant"+intruderPosition);
			deplacement(i,3,i.getAgility());
			System.out.println("apres qui doit etre avant"+intruderPosition);
			System.out.println("apres"+i.getPosition());
			if(!iHasmoved) {
				deplacement(i,1,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,0,i.getAgility());
			}

		}
		else if((lineI>lineG) && (columnG==columnI)){
			System.out.println("avant"+intruderPosition);

			deplacement(i,2,i.getAgility());
			System.out.println("apres qui doit etre avant"+intruderPosition);
			System.out.println("apres"+i.getPosition());
			if(!iHasmoved) {
				deplacement(i,1,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,0,i.getAgility());
			}

		}
	}

	

	
	public void deplapriorite(Intruder i) {
			i.setVisionzone();
			boolean havesearchitem=false;
			List<Block> ivision=i.getVisionzone();
			for(Block b:ivision) {
				for(Item it:items) {
					if(it.getPosition().equals(b)) {
						goitem(i,it);
						havesearchitem=true;
						break;
					}
				}
				if(havesearchitem) {
					break;
				}
			}
			if(!havesearchitem) {
				deplaleatoireI(i);
			}
	}
	
	public void goitem(Intruder i,Item g) {

		Block itemPos=g.getPosition();
		Block intruderPosition=i.getPosition();
		int lineIt=itemPos.getLine();
		int columnIt=itemPos.getColumn();
		int lineI=intruderPosition.getLine();
		int columnI=intruderPosition.getColumn();
		if((lineI==lineIt) && (columnIt<columnI)) {
			if(columnI-columnIt==1) {
				deplacement(i,1,1);
			}
			else {
				deplacement(i,1,i.getAgility());
			}			
		}
		else if((lineI==lineIt) && (columnIt>columnI)) {
			if(columnIt-columnI==1) {
				deplacement(i,0,1);
			}
			else {
				deplacement(i,0,i.getAgility());
			}
			
			
		}
		else if((lineI<lineIt) && (columnIt==columnI)) {
			if(lineIt-lineI==1) {
				deplacement(i,2,1);
			}
			else {
				deplacement(i,2,i.getAgility());
			}
		}
		else {
			if(lineI-lineIt==1) {
				deplacement(i,3,1);
			}
			else {
				deplacement(i,3,i.getAgility());
			}
		}
	}

	public void deplacement(Intruder a,int direction, int deplacement) {
		
		
		
		if(direction==0&&isOk(a,direction,deplacement)) {
			a.getPosition().setColumn(a.getPosition().getColumn()+deplacement);
		}
		else if(direction==1&&isOk(a,direction,deplacement)) {
			a.getPosition().setColumn(a.getPosition().getColumn()-deplacement);
		}
		
		else if(direction==2&&isOk(a,direction,deplacement)) {
			a.getPosition().setLine(a.getPosition().getLine()+deplacement);
		}
		else if(direction==3&&isOk(a,direction,deplacement)) {
			a.getPosition().setLine(a.getPosition().getLine()-deplacement);
		}
	
	}

	public void deplacement(Guardian a,int direction) {
		
		
	
	
		if(direction==0&&isOk(a,direction)) {
			a.getPosition().setColumn(a.getPosition().getColumn()+a.getAgility());
		}
		else if(direction==1&&isOk(a,direction)) {
			a.getPosition().setColumn(a.getPosition().getColumn()-a.getAgility());
		}
		
		else if(direction==2&&isOk(a,direction)) {
			a.getPosition().setLine(a.getPosition().getLine()+a.getAgility());
		}
		else if(direction==3&&isOk(a,direction)) {
			a.getPosition().setLine(a.getPosition().getLine()-a.getAgility());
		}
	
	}
	
	public void deplaleatoireI(Intruder a) {
		boolean hasmoved=false;
		while(!hasmoved) {
			int rdm=getRandomNumber(0,3);
			
		
	
			if(rdm==0&&isOk(a,0,a.getAgility())) {
				a.getPosition().setColumn(a.getPosition().getColumn()+a.getAgility());
				hasmoved=true;
			}
			else if(rdm==1&&isOk(a,1,a.getAgility())) {
				a.getPosition().setColumn(a.getPosition().getColumn()-a.getAgility());
				hasmoved=true;
			}
			
			else if(rdm==2&&isOk(a,2,a.getAgility())) {
				a.getPosition().setLine(a.getPosition().getLine()+a.getAgility());
				hasmoved=true;
			}
			else if(rdm==0&&isOk(a,3,a.getAgility())) {
				a.getPosition().setLine(a.getPosition().getLine()-a.getAgility());
				hasmoved=true;
			}
		}

	
}
	
	
	public void deplaleatoireG() {
		
		for(Guardian a : guardians) {
			iHasmoved=false;
			while(!iHasmoved) {
				int rdm=getRandomNumber(0,3);
				
			
		
				if(rdm==0&&isOk(a,0)) {
					a.getPosition().setColumn(a.getPosition().getColumn()+a.getAgility());
					iHasmoved=true;
				}
				else if(rdm==1&&isOk(a,1)) {
					a.getPosition().setColumn(a.getPosition().getColumn()-a.getAgility());
					iHasmoved=true;
				}
				
				else if(rdm==2&&isOk(a,2)) {
					a.getPosition().setLine(a.getPosition().getLine()+a.getAgility());
					iHasmoved=true;
				}
				else if(rdm==0&&isOk(a,3)) {
					a.getPosition().setLine(a.getPosition().getLine()-a.getAgility());
					iHasmoved=true;
				}
			}

		}
		
	}
	
	public boolean isOk(Intruder i,int mvt, int deplacement){
		if (mvt==0&&i.getPosition().getColumn()+deplacement<GameConfiguration.COLUMN_COUNT) {
			iHasmoved=true;
			return true;
		}
		else if (mvt==1&&i.getPosition().getColumn()-deplacement>-1) {
			iHasmoved=true;
			return true;
		}
		else if (mvt==2&&i.getPosition().getLine()+deplacement<GameConfiguration.LINE_COUNT) {
			iHasmoved=true;
			return true;
		}
		else if (mvt==3&&i.getPosition().getLine()-deplacement>-1) {
			iHasmoved=true;
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public boolean isOk(Guardian i,int mvt){
		if (mvt==0&&i.getPosition().getColumn()+i.getAgility()<GameConfiguration.COLUMN_COUNT) {
			return true;
		}
		else if (mvt==1&&i.getPosition().getColumn()-i.getAgility()>-1) {
			return true;
		}
		else if (mvt==2&&i.getPosition().getLine()+i.getAgility()<GameConfiguration.LINE_COUNT) {
			return true;
		}
		else if (mvt==3&&i.getPosition().getLine()-i.getAgility()>-1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	

	

	

	
	public void exitintruders() {
		List<Intruder> eliminatedintruders = new ArrayList<Intruder>();
		for (Intruder intruder : intruders) {
			Block intruderPosition = intruder.getPosition();
			if(gate.getPosition().equals(intruderPosition)) {
				eliminatedintruders.add(intruder);
			}
		}
		for(Intruder intruder:eliminatedintruders) {
			intruders.remove(intruder);
			freeintruders.add(intruder);
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
	
	public List<Intruder> getFreeintruders() {
		return freeintruders;
	}

	public void setFreeintruders(List<Intruder> freeintruders) {
		this.freeintruders = freeintruders;
	}
	
	public ExitGate getGate() {
		return gate;
	}

	public void setGate(ExitGate gate) {
		this.gate = gate;
	}





	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
}
