package process;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

import config.GameConfiguration;
import map.Block;
import map.Map;
import model.ExitGate;
import model.Guardian;
import model.Intruder;
import model.Item;
import sounds.Sound;


/**
* this class contains essentially the same methods as MobileElementManager but we separate them to distinguish them well. It allows the mobility of our data in the game
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class MobileElementManagerGame {
	Intruder intruder;
	private Map map;
	private int round;
	private boolean lureappareance=false;
	private boolean filettrap=false;
	private boolean filetappareance=false;

	private boolean droppeditem=false;
	private boolean droppedmoney=false;
	private List<Item> items = new ArrayList<Item>();
	private Item mlure;
	private Item mfilet;
	
	private int duels;
	private int arrestations;
	private int countcloak;
	private int countfilet;
	private int countlure;
	private Sound netSound;
	private Sound incendie;







	private List<Guardian> guardians = new ArrayList<Guardian>();
	private ExitGate gate;
	private List<Intruder> freeintruders = new ArrayList<Intruder>();
	private int totalmoney=0;
	private int roundmoney=0;
	private int exitmoney=0;

	

	





	public MobileElementManagerGame(Map map) {
		this.map = map;
		this.round=0;
		this.countlure=20;
		this.countfilet=7;
		incendie=new Sound();
		incendie.setFile(8);
		netSound=new Sound();
		netSound.setFile(9);
		this.exitmoney=0;
		this.countcloak=20;
		this.arrestations=0;
		this.duels=0;

	}
	

	
	

	public void add(Item item) {
		items.add(item);
	}
	


	public void add(Guardian guardian) {
		guardians.add(guardian);
	}


	
	








	public void nextRound() {
		if(intruder!=null) {
			if(isNumber(50)) {
				generateGuardian();
			}
			if(isNumber(20)) {
				while(!droppeditem) {
					generateItem();
				}
				droppeditem=false;
			}
			
			if(isNumber(10)) {
				while(!droppedmoney) {
					generateMoney();
				}
				droppedmoney=false;
			}
			
			if(!lureappareance) {
				gSEEi();
			}
			else {
				gGOlure();
			}
			
			exitintruders();
			evolution();
			moneyearned();
			combat();
			incrementRound();
		}



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
		if(items.size()==0) {
			droppeditem=true;
			String nm=randomItem();
			Item item = new Item(position,nm);

			add(item);
		}
		else {
			for(Item i:items) {
				if(i.getPosition().equals(position)||position.equals(gate.getPosition())) {

					droppeditem=false;
					break;
				}
				else {
					
					droppeditem=true;
				}
				
			}
			if(droppeditem) {
				String nm=randomItem();
				Item item = new Item(position,nm);
				add(item);
			}
		}

		}
	
	
	


	
	private void generateMoney() {
		int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
		int lineColumn = getRandomNumber(0, GameConfiguration.LINE_COUNT - 1);
		Block position = new Block(lineColumn, randomColumn);
		if(items.size()==0) {
			droppedmoney=true;
			String nm="Money";
			Item item = new Item(position,nm);
			item.setNbre(100);

			add(item);
		}
		else {
			for(Item i:items) {
				if(i.getPosition().equals(position)||position.equals(gate.getPosition())) {

					droppedmoney=false;
					break;
				}
				else {
					
					droppedmoney=true;
				}
			}
			if(droppedmoney) {
				String nm="Money";
				
				Item item = new Item(position,nm);
				item.setNbre(100);
				add(item);
			}
		}
		
		}
	
	public String randomItem() {
		HashMap<String,String> rdm = new HashMap<String,String>();
		rdm.put("0", "Agility Potion");
		rdm.put("1", "Vision Potion");
		rdm.put("13", "Precision Potion");
		rdm.put("14", "Precision Potion");
		rdm.put("2", "Precision Potion");
		rdm.put("6", "Precision Potion");
		rdm.put("7", "Precision Potion");
		rdm.put("10", "Precision Potion");
		rdm.put("11", "Precision Potion");
		rdm.put("9", "Filet");
		rdm.put("3", "Filet");
		rdm.put("12", "Precision Potion");
		rdm.put("8", "Lure");
		rdm.put("4", "Lure");
		rdm.put("5", "Invisibility cloak");
		int r=getRandomNumber(0,14);
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

						Item i=item;
						if(i.getName()!="Money") {
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
						else {
							usefilet(guardian,guardian.getPosition().getLine(),guardian.getPosition().getColumn());
						}
						
					}
					}
				}
			for(Item item:eliminatedItem) {
				items.remove(item);
			}			}
	

	
	public void evolutionI() {
		if(!intrudervoid()) {
			List<Item> eliminatedItem = new ArrayList<Item>();
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
							intruder.incrementM(i.getNbre());
							roundmoney+=i.getNbre();
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
							if(intruder.getVision() <= 4) {
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
				
			for(Item item:eliminatedItem) {
				items.remove(item);
			}
		}
		}
	
	public void uselure() {
		Block intruderPosition = new Block(intruder.getPosition().getLine(),intruder.getPosition().getColumn());
		HashMap<String,Item> itemsI=intruder.getItems();
		Item lure=itemsI.get("Lure");
		if(lure.getNbre()>0 ) {
			lureappareance=true;

			incendie.play();
			incendie.loop();
			lure.decrement(1);
			itemsI.put("Lure", lure);
			intruder.setItems(itemsI);
			Block lureposition=intruderPosition;
			mlure=new Item(lureposition,0,"Mobile Lure");
			
			
		}
	}
	
	public void stoplure() {
		mlure=null;
		incendie.stop();
		countlure=20;
		lureappareance=false;
	}
	
	public void usecloak() {
		HashMap<String,Item> itemsI=intruder.getItems();
		Item cloak=itemsI.get("Invisibility cloak");
		if(cloak.getNbre()>0 ) {
			intruder.setTransparent(true);
			//cloak.play();
			cloak.decrement(1);
			itemsI.put("Invisibility cloak", cloak);
			intruder.setItems(itemsI);

			
			
		}
	}
	
	public void stopcloak(Intruder i) {
		countcloak=20;;
		i.setTransparent(false);
	}
		
		public void usefilet(Guardian guardian, int line, int column) {
			Block guardianPosition = new Block(line,column);
			HashMap<String,Item> itemsG=guardian.getItems();
			Item filet=itemsG.get("Filet");
			if(filet.getNbre()>0 && filetappareance==false) {
				Sound s=new Sound();
				s.setFile(6);
				s.play();
				filetappareance=true;
				netSound.play();
				filet.decrement(1);
				itemsG.put("Filet", filet);
				guardian.setItems(itemsG);
				Block filetposition=guardianPosition;
				mfilet=new Item(filetposition,0,"Mobile Filet");
				
				
			}
		
	
				
			
	
		}
		
		public void stopfilet() {
			netSound.stop();
			mfilet=null;
			countfilet=7;
			filettrap=false;
			filetappareance=false;
		}
		
		public void iONfilet(Intruder intruder) {
			filettrap=true;	
		}
	

			
	public void combat() {
		if(mlure!=null) {
			lureappareance=true;
		}
		if(lureappareance==true) {

			countlure--;
			if(countlure==0) {
				stoplure();
			}
		}
		List<Guardian> eliminatedGuardian = new ArrayList<Guardian>();
		for (Guardian guardian : guardians) {
			Block guardianPosition = guardian.getPosition();
			

				if (!intrudervoid() && intruder.getPosition().equals(guardianPosition) && !intruder.isTransparent()) {
					Sound s=new Sound();
					if(guardian.getPrecision()<intruder.getDodge()) {
						s.setFile(3);
						s.play();
						intruder.decrementP(2);
						eliminatedGuardian.add(guardian);
						duels+=1;
					}
					else {
						if(filetappareance) {
							stopfilet();
						}
						s.setFile(2);
						s.play();
						String nm="Money";
						Item item = new Item(intruder.getPosition(),nm);
						item.setNbre(intruder.getMoneyearned());
						add(item);
						guardian.decrementP(1);
						totalmoney-=intruder.moneyearned;
						intruder=null;
						arrestations+=1;
					}
				}
				
			}
		for(Guardian guardian:eliminatedGuardian) {
			guardians.remove(guardian);
		}	

	}
	

	
	
	public void gGOlure(){
		for(Guardian a : guardians) {
			Block lureposition=mlure.getPosition();
			int lureline=lureposition.getLine();
			int lurecolumn=lureposition.getColumn();
			int guardianline=a.getPosition().getLine();
			int guardiancolumn=a.getPosition().getColumn();
			if(lureline<guardianline) {
				if(guardianline-lureline==1) {
					deplacement(a,3,1);
				}
				else {
					deplacement(a,3,a.getAgility());
				}
			}
			else if(lureline>guardianline){
				if(lureline-guardianline==1) {
					deplacement(a,2,1);
				}
				else {
					deplacement(a,2,a.getAgility());
				}			
			}
			else if(lurecolumn>guardiancolumn) {
				if(lurecolumn-guardiancolumn==1) {
					deplacement(a,0,1);
				}
				else {
					deplacement(a,0,a.getAgility());
				}
			}
			else if(lurecolumn<guardiancolumn){
				if(guardiancolumn-lurecolumn==1) {
					deplacement(a,1,1);
				}
				else {
					deplacement(a,1,a.getAgility());
				}
			}
		}
		
	}
	
	
	public void gSEEi(){
		for(Guardian a : guardians) {
			a.setVisionzone();
			boolean havefocus=false;
			List<Block> gvision=a.getVisionzone();
			for(Block b:gvision) {
					if(intruder.getPosition().equals(b)&&!intruder.isTransparent()) {
						gointruder(a,intruder);
						havefocus=true;
						break;
					}
				
				if(havefocus) {
					break;
				}
			}
			if(!havefocus) {
				deplapriorite(a);
			}
		}
		
	}
	
	public void gointruder(Guardian gu,Intruder in) {

		Block intruderPos=in.getPosition();
		Block guardianPosition=gu.getPosition();
		int lineI=intruderPos.getLine();
		int columnI=intruderPos.getColumn();
		int lineG=guardianPosition.getLine();
		int columnG=guardianPosition.getColumn();
		if((lineG==lineI) && (columnI<columnG)) {
			if(columnG-columnI==1) {
				deplacement(gu,1,1);
			}
			else {
				deplacement(gu,1,gu.getAgility());
			}			
		}
		else if((lineG==lineI) && (columnI>columnG)) {
			if(columnI-columnG==1) {
				deplacement(gu,0,1);
			}
			else {
				deplacement(gu,0,gu.getAgility());
			}
			
			
		}
		else if((lineG<lineI) && (columnI==columnG)) {
			if(lineI-lineG==1) {
				deplacement(gu,2,1);
			}
			else {
				deplacement(gu,2,gu.getAgility());
			}
		}
		else {
			if(lineG-lineI==1) {
				deplacement(gu,3,1);
			}
			else {
				deplacement(gu,3,gu.getAgility());
			}
		}

		}

	

	

	
	public void deplapriorite(Guardian i) {
			i.setVisionzone();
			boolean havesearchitem=false;
			List<Block> ivision=i.getVisionzone();
			for(Block b:ivision) {
				for(Item it:items) {
					if(it.getPosition().equals(b)&&it.getName()!="Money") {
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
				deplaleatoireG(i);
			}
	}
	
	public void goitem(Guardian i,Item g) {

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
	
	
	
	
	public void deplacement(Block deplacement) {
		if(mfilet!=null) {
			filetappareance=true;
		}
		if(filetappareance) {
			if(filettrap) {
				countfilet--;
				if(countfilet==0) {
					stopfilet();
				}
			}
		}
		if(intruder.isTransparent()) {

			countcloak--;
			if(countcloak==0) {
				intruder.setTransparent(false);
			}
			
		}
		if(mfilet!=null&&intruder.getPosition().equals(mfilet.getPosition())) {
			iONfilet(intruder);
		}
		if(!filettrap) {
			intruder.setPosition(deplacement);
		}
	}

	public void deplacement(Guardian a,int direction, int deplacement) {
		
		
	
	
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
	

	
	
	public void deplaleatoireG(Guardian a) {
		
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
			else if(rdm==3&&isOk(a,3,a.getAgility())) {
				a.getPosition().setLine(a.getPosition().getLine()-a.getAgility());
				hasmoved=true;
			}
		}
		
	}
	
	
	
	
	public boolean isOk(Guardian i,int mvt,int deplacement){
		if (mvt==0&&i.getPosition().getColumn()+deplacement<GameConfiguration.COLUMN_COUNT) {
			return true;
		}
		else if (mvt==1&&i.getPosition().getColumn()-deplacement>-1) {
			return true;
		}
		else if (mvt==2&&i.getPosition().getLine()+deplacement<GameConfiguration.LINE_COUNT) {
			return true;
		}
		else if (mvt==3&&i.getPosition().getLine()-deplacement>-1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean intrudervoid() {
		return intruder==null;
	}
	



	
	public void exitintruders() {

			Block intruderPosition = intruder.getPosition();
			if(gate.getPosition().equals(intruderPosition)) {
				exitmoney+=intruder.moneyearned;
				intruder=null;
			}
		


	}
	
	public void moneyearned() {
		totalmoney+=roundmoney;
		roundmoney=0;
		

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

	public Intruder getIntruders() {
		return intruder;
	}

	public void setIntruders(Intruder intruders) {
		this.intruder = intruders;
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

	public Item getMlure() {
		return mlure;
	}





	public int getCountcloak() {
		return countcloak;
	}





	public void setCountcloak(int countcloak) {
		this.countcloak = countcloak;
	}





	public int getCountlure() {
		return countlure;
	}





	public void setCountlure(int countlure) {
		this.countlure = countlure;
	}





	public void setMlure(Item mlure) {
		this.mlure = mlure;
	}
	public Item getMfilet() {
		return mfilet;
	}





	public void setMfilet(Item mfilet) {
		this.mfilet = mfilet;
	}
	
	
	public int getTotalmoney() {
		return totalmoney;
	}





	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	








	public int getRound() {
		return round;
	}





	public void setRound(int round) {
		this.round = round;
	}











	public int getDuels() {
		return duels;
	}





	public void setDuels(int duels) {
		this.duels = duels;
	}





	public int getArrestations() {
		return arrestations;
	}





	public void setArrestations(int arrestations) {
		this.arrestations = arrestations;
	}





	public int getExitmoney() {
		return exitmoney;
	}





	public void setExitmoney(int exitmoney) {
		this.exitmoney = exitmoney;
	}

	




	public Sound getIncendie() {
		return incendie;
	}





	public void setIncendie(Sound incendie) {
		this.incendie = incendie;
	}





	public boolean isFilettrap() {
		return filettrap;
	}





	public void setFilettrap(boolean filettrap) {
		this.filettrap = filettrap;
	}





	public int getCountfilet() {
		return countfilet;
	}





	public void setCountfilet(int countfilet) {
		this.countfilet = countfilet;
	}





	private static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}



	

}
