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
* allows the mobility of our data in the simulation
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/

public class MobileElementManager {
	private Map map;
	private int round;
	private boolean lureappareance=false;
	private boolean filettrap=false;
	private boolean filetappareance=false;
	private boolean siffletappareance=false;

	private boolean iHasmoved=false;
	private boolean droppeditem=false;
	private boolean droppedmoney=false;
	private List<Item> items = new ArrayList<Item>();
	private Item mlure;
	private Item mfilet;
	private Item msifflet;

	
	private int duels;
	private int arrestations;
	private int countsifflet;
	private int countfilet;
	private int countlure;
	private Sound siffletsound;
	private Sound incendie;
	private Sound netSound;








	private List<Guardian> guardians = new ArrayList<Guardian>();
	private List<Intruder> intruders = new ArrayList<Intruder>();
	private ExitGate gate;
	private int totalmoney=0;
	private int roundmoney=0;
	private int exitmoney=0;

	

	





	public MobileElementManager(Map map) {
		this.map = map;
		this.round=0;
		this.countlure=20;
		this.countfilet=7;
		this.countsifflet=3;
		incendie=new Sound();
		incendie.setFile(8);
		siffletsound=new Sound();
		siffletsound.setFile(10);
		netSound=new Sound();
		netSound.setFile(9);
		this.exitmoney=0;
		this.duels=0;
		this.arrestations=0;

	}
	

	
	
	/**
	 * add an item in our list
	 * @param item The item we want to add
	 */
	public void add(Item item) {
		items.add(item);
	}
	

	/**
	 * add a guardian in our list
	 * @param guardian The guardian we want to add
	 */
	public void add(Guardian guardian) {
		guardians.add(guardian);
	}

	/**
	 * add an intruder in our list
	 * @param intruder The intruder we want to add
	 */
	public void add(Intruder intruder) {
		intruders.add(intruder);
	}
	
	

	
	







	/**
	 * this method is called to perform the actions to be done each round
	 */
	public void nextRound() {

		if(isNumber(100)) {
			generateGuardian();
		}
		if(isNumber(30)) {
			while(!droppeditem) {
				generateItem();
			}
			droppeditem=false;
			System.out.println("a");
		}
		
		if(isNumber(20)) {
			while(!droppedmoney) {
				generateMoney();
			}
			droppedmoney=false;
		}
		if(isNumber(2)) {
			goExit();
			iSEEg();
		}
		else {
			if(!lureappareance&&!siffletappareance) {
				gSEEi();
			}
			else if(lureappareance){
				gGOlure();
			}
			else if(siffletappareance) {
				focusintruder();
			}
		}

		evolution();
		combat();
		moneyearned();

		incrementRound();



	}
	
	/**
	 * verify if the actual round is a multiple of i
	 * @param i The number we want to verify
	 * @return boolean true or false 
	 */
	public boolean isNumber(int i) {
		if(round%i==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * generate a guardian and place it randomly on one of the blocks located at the top of the map
	 */
	public void generateGuardian() {
		int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
		Block position = new Block(0, randomColumn);
		Guardian guardian = new Guardian(position);
		guardian.inititem();
		add(guardian);
	}
	
	/**
	 * generate an item and place it randomly on one of the blocks of the map. An item cannot appear above another
	 */
	public void generateItem() {
		int randomColumn = getRandomNumber(0, GameConfiguration.COLUMN_COUNT - 1);
		int lineColumn = getRandomNumber(0, GameConfiguration.LINE_COUNT - 1);
		Block position = new Block(lineColumn, randomColumn);
		if(items.size()==0) {
			droppeditem=true;
			String nm=randomItem();
			Item item = new Item(position,nm);
			System.out.println(round+" "+item.getPosition());

			add(item);
		}
		else {
			for(Item i:items) {
				if(i.getPosition().equals(position)||position.equals(gate.getPosition())) {
					System.out.println(round+" error mon"+i.getPosition());

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
				System.out.println(round+" "+item.getPosition());
				add(item);
			}
		}

		}
	
	
	


	/**
	 * generate a bag of money and place it randomly on one of the blocks of the map. A bag cannot appear above another
	 */
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
					System.out.println(round+" error it"+i.getPosition());

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
				System.out.println(round+" "+item.getPosition());
				item.setNbre(100);
				add(item);
			}
		}
		
		}
	
	/**
	 * Choose an item at random. each item has a different spawn percentage depending on their effectiveness
	 * @return the name of the item randomly drawn 
	 */
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
	
	
	/**
	 * increment the value of the attribut round 
	 */
	public void incrementRound() {
		round+=1;
	}

	/**
	 * calls the 2 evolution methods (for guardian and intruder)
	 */
	public void evolution() {
		evolutionI();
		evolutionG();
	}
	
	/**
	 * allows you to improve the abilities of the guardian (vision,net,etc) if he is on the same block as an item
	 */
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
	

	/**
	 * allows you to improve the abilities of the intruder (vision,lure,etc) if he is on the same block as an item
	 */
	public void evolutionI() {
		List<Item> eliminatedItem = new ArrayList<Item>();
		for (Intruder intruder : intruders) {
			Block intruderPosition = intruder.getPosition();
			for (Item item : items) {
				if (item.getPosition().equals(intruderPosition)) {
					System.out.println("same");
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
			}
		for(Item item:eliminatedItem) {
			items.remove(item);
		}	
		}
	
	
	/**
	 * make the lure active 
	 * @param intruder The intruder who use the lure
	 * @param line Line where the lure is created 
	 * @param column Column where the lure is created 
	 */
	public void uselure(Intruder intruder, int line, int column) {
		Block intruderPosition = new Block(line,column);
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
	
	
	/**
	 * make the whistle active 
	 * @param guardian The guardian who use the whistle
	 * @param line Line where the whistle is created 
	 * @param column Column where the whistle is created 
	 */
	public void usesifflet(Guardian guardian, int line, int column) {
		Block intruderPosition = new Block(line,column);
		HashMap<String,Item> itemsG=guardian.getItems();
		Item sifflet=itemsG.get("Sifflet");
		if(sifflet.getNbre()>0 && sifflet!=null) {
			siffletappareance=true;
			siffletsound.play();
			sifflet.decrement(1);
			itemsG.put("Sifflet", sifflet);
			guardian.setItems(itemsG);
			Block siffletposition=intruderPosition;
			msifflet=new Item(siffletposition,0,"Sifflet");
			
			
		}
	}
	
	/**
	 * deactivate the whistle 
	 */
	public void stopsifflet() {
		siffletsound.stop();
		msifflet=null;
		countsifflet=3;
		siffletappareance=false;
	}
	
	/**
	 * deactivate the lure 
	 */
	public void stoplure() {
		incendie.stop();
		mlure=null;
		countlure=20;
		lureappareance=false;
	}
	
	/**
	 * make the cloak of the intruder active 
	 * @param intruder The intruder who use the lure
	 */
	public void usecloak(Intruder intruder) {
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
	
	/**
	 * deactivate the cloak 
	 * @param i The intruder whose ability is removed
	 */
	public void stopcloak(Intruder i) {
		//cloak.stop();
		i.setTransparentcount(15);
		i.setTransparent(false);
	}
		
	
	/**
	 * make the net of the guardian active 
	 * @param guardian The guardian who use the net
	 * @param line Line where the net is created 
	 * @param column Column where the lure is net 
	 */
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
		
		
		/**
		 * deactivate the filet 
		 */
		public void stopfilet() {
			netSound.stop();
			mfilet=null;
			countfilet=7;
			filettrap=false;
			filetappareance=false;
		}
		
		
		/**
		 * sends a signal that an intruder has been captured
		 */
		public void iONfilet() {
			filettrap=true;	
		}
	

	/**
	 * if an intruder and a guardian are on the same block, remove the person with the least precision(guardian attribute name)/dodge(intruder attribute name)
	 */	
	public void combat() {
		System.out.println("round :"+round+" countlure : "+countlure);
		if(mlure!=null) {
			lureappareance=true;
		}
		if(lureappareance==true) {

			countlure--;
			if(countlure==0) {
				stoplure();
			}
		}

		List<Intruder> eliminatedIntruder = new ArrayList<Intruder>();
		List<Guardian> eliminatedGuardian = new ArrayList<Guardian>();
		for (Guardian guardian : guardians) {
			Block guardianPosition = guardian.getPosition();
			for (Intruder intruder : intruders) {

				if (intruder.getPosition().equals(guardianPosition) && !intruder.isTransparent()) {
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
						createlure();
						s.setFile(2);
						s.play();
						String nm="Money";
						Item item = new Item(intruder.getPosition(),nm);
						item.setNbre(intruder.getMoneyearned());
						add(item);
						guardian.decrementP(1);
						totalmoney-=intruder.moneyearned;
						eliminatedIntruder.add(intruder);
						arrestations+=1;
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
	
	/**
	 * pick an intruder in those present and create a lure on his position
	 */
	public void createlure() {
		Intruder i=intruders.get(getRandomNumber(0,intruders.size()-1));
		uselure(i,i.getPosition().getLine(),i.getPosition().getColumn());
	}
	

	/**
	 * if the whistle is active, warned guardians focus the intruder seen
	 */
	public void focusintruder() {
		if(msifflet!=null) {
			siffletappareance=true;
		}
		if(siffletappareance==true) {
			countsifflet--;
			if(countsifflet==0) {
				stopsifflet();
			}
		}
		if(msifflet!=null) {
			for(Guardian a:guardians) {
				
				a.setVisionzone();
				List<Block> gvision=a.getVisionzone();
				for(Block b:gvision) {
					for(Intruder i:intruders) {
						if(i.getPosition().equals(b)) {
							gointruder(a,i);
							break;
						}
						else {
	
	 
							Block iposition=msifflet.getPosition();
							int iline=iposition.getLine();
							int icolumn=iposition.getColumn();
							int guardianline=a.getPosition().getLine();
							int guardiancolumn=a.getPosition().getColumn();
							if(iline<guardianline) {
								if(guardianline-iline==1) {
									deplacement(a,3,1);
								}
								else {
									deplacement(a,3,a.getAgility());
								}
							}
							else if(iline>guardianline){
								if(iline-guardianline==1) {
									deplacement(a,2,1);
								}
								else {
									deplacement(a,2,a.getAgility());
								}			
							}
							else if(icolumn>guardiancolumn) {
								if(icolumn-guardiancolumn==1) {
									deplacement(a,0,1);
								}
								else {
									deplacement(a,0,a.getAgility());
								}
							}
							else if(icolumn<guardiancolumn){
								if(guardiancolumn-icolumn==1) {
									deplacement(a,1,1);
								}
								else {
									deplacement(a,1,a.getAgility());
								}
							}
	
					}
					break;
						
				}
				break;
					
			}
		}
	}
			
				
		
	}
	
	
	/**
	 * when a lure is active, guardians will move towards the lure
	 */
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
	
	/**
	 * first priority of the guardians. they will check if there is an intruder in their field of view
	 */
	public void gSEEi(){

		for(Guardian a : guardians) {
			a.setVisionzone();
			boolean havefocus=false;
			List<Block> gvision=a.getVisionzone();
			for(Block b:gvision) {
				for(Intruder i:intruders) {
					if(i.getPosition().equals(b)&&!i.isTransparent()) {
						gointruder(a,i);
						if(guardians.size()>1&&!i.isTransparent()&&a.getVision()>1&&isInGVision(a)) {
							usesifflet(a,i.getPosition().getLine(),i.getPosition().getColumn());
						}
						havefocus=true;
						break;
					}
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
	
	/**
	 * if an intruder is in the field of vision of one of the guardians then the guardian moves towards this one
	 * @param gu The guardian who move 
	 * @param in The intruder who is targeted
	 */
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

	

	

	/**
	 * second priority of the guardians. they will check if there is an item in their field of view
	 * @param i A guardian
	 */
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
	
	/**
	 * if an item is in the field of vision of one of the guardians then the guardian moves towards this one
	 * @param i The guardian who move
	 * @param g The item that will be taken
	 */
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
	
	/**
	 * third priority of the guardians. move to a random block
	 * @param a The guardian who move
	 */
	public void deplaleatoireG(Guardian a) {
		
		boolean hasmoved=false;
		while(!hasmoved) {
			int rdm=getRandomNumber(0,3);
			
		
	
			if(rdm==0&&isOk(a,0,a.getAgility())) {
				//a.getPosition().setColumn(a.getPosition().getColumn()+a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine(),a.getPosition().getColumn()+a.getAgility()));

				hasmoved=true;
			}
			else if(rdm==1&&isOk(a,1,a.getAgility())) {
				//a.getPosition().setColumn(a.getPosition().getColumn()-a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine(),a.getPosition().getColumn()-a.getAgility()));

				hasmoved=true;
			}
			
			else if(rdm==2&&isOk(a,2,a.getAgility())) {
				//a.getPosition().setLine(a.getPosition().getLine()+a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine()+a.getAgility(),a.getPosition().getColumn()));

				hasmoved=true;
			}
			else if(rdm==3&&isOk(a,3,a.getAgility())) {
				//a.getPosition().setLine(a.getPosition().getLine()-a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine()-a.getAgility(),a.getPosition().getColumn()));

				hasmoved=true;
			}
		}
		
	}
	
	/**
	 * method who move the guardian on the map thanks to a direction
	 * @param a The guardian who move 
	 * @param direction The direction he will take
	 * @param deplacement The number of case we want to move 
	 */
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
	
	
	/**
	 * check if the to move case is accessible
	 * @param i The guardian who move 
	 * @param mvt The direction he will take
	 * @param deplacement The number of case we want to move 
	 * @return true if the case is accessible, false else
	 */
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
	
	/**
	 * first priority of the intruder. they will check if there is a guardian in their field of view
	 */
	public void iSEEg(){
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



		for(Intruder a : intruders) {
			a.setVisionzone();
			boolean haverunaway=false;
			List<Block> ivision=a.getVisionzone();
			if(a.isTransparent()&& a.getTransparentcount()>0) {

				usecloak(a);
				a.decrementIC();
			}
			else if(a.getTransparentcount() ==0) {
				stopcloak(a);
			}
			for(Block b:ivision) {
				for(Guardian g:guardians) {
					if(mfilet!=null&&a.getPosition().equals(mfilet.getPosition())) {
						haverunaway=true;
						iONfilet();
					}



					
					else if(g.getPosition().equals(b) && !a.isTransparent()) {
						if(guardians.size()>1) {
							usecloak(a);
						}
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
	
	
	
	/**
	 * runaway from the guardian seen
	 * @param i The intruder who move 
	 * @param g The guardian that the intruder wants to escape
	 */
	public void runaway(Intruder i,Guardian g) {

		Block guardianPos=g.getPosition();
		Block intruderPosition=i.getPosition();
		int lineG=guardianPos.getLine();
		int columnG=guardianPos.getColumn();
		int lineI=intruderPosition.getLine();
		int columnI=intruderPosition.getColumn();
		iHasmoved=false;
		if((lineI==lineG) && (columnG<columnI)) {

			deplacement(i,0,i.getAgility());
			if(!iHasmoved) {
				deplacement(i,3,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,2,i.getAgility());
			}

		}
		else if((lineI==lineG) && (columnG>columnI)) {

			deplacement(i,1,i.getAgility());


			if(!iHasmoved) {
				deplacement(i,3,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,2,i.getAgility());
			}

		}
		else if((lineI<lineG) && (columnG==columnI)) {
			deplacement(i,3,i.getAgility());

			if(!iHasmoved) {
				deplacement(i,1,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,0,i.getAgility());
			}

		}
		else if((lineI>lineG) && (columnG==columnI)){

			deplacement(i,2,i.getAgility());
			if(!iHasmoved) {
				deplacement(i,1,i.getAgility());
			}
			if(!iHasmoved) {
				deplacement(i,0,i.getAgility());
			}

		}
	}

	
	/**
	 * second priority of the intruders. they will check if there is an item in their field of view
	 * @param i An intruder
	 */
	
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
	
	
	/**
	 * if an item is in the field of vision of one of the intruders then the intruders moves towards this one
	 * @param i The intruder who move
	 * @param g The item that will be taken
	 */
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
		else if((lineI>lineIt) && (columnIt==columnI)){
			if(lineI-lineIt==1) {
				deplacement(i,3,1);
			}
			else {
				deplacement(i,3,i.getAgility());
			}
		}
	}
	
	/**
	 * method who move the intruder on the map thanks to a direction
	 * @param a The intruder who move 
	 * @param direction The direction he will take
	 * @param deplacement The number of case we want to move 
	 */
	public void deplacement(Intruder a,int direction, int deplacement) {
		
		
		
		if(direction==0&&isOk(a,direction,deplacement)) {
			//a.getPosition().setColumn(a.getPosition().getColumn()+deplacement);
			a.setPosition(new Block(a.getPosition().getLine(),a.getPosition().getColumn()+deplacement));
		}
		else if(direction==1&&isOk(a,direction,deplacement)) {
			//a.getPosition().setColumn(a.getPosition().getColumn()-deplacement);
			a.setPosition(new Block(a.getPosition().getLine(),a.getPosition().getColumn()-deplacement));

		}
		
		else if(direction==2&&isOk(a,direction,deplacement)) {
			//a.getPosition().setLine(a.getPosition().getLine()+deplacement);
			a.setPosition(new Block(a.getPosition().getLine()+deplacement,a.getPosition().getColumn()));

			
		}
		else if(direction==3&&isOk(a,direction,deplacement)) {
			//a.getPosition().setLine(a.getPosition().getLine()-deplacement);
			a.setPosition(new Block(a.getPosition().getLine()-deplacement,a.getPosition().getColumn()));

		}
	
	}


	/**
	 * third priority of the intruders. move to a random block
	 * @param a The intruder who move
	 */
	public void deplaleatoireI(Intruder a) {
		boolean hasmoved=false;
		while(!hasmoved) {
			int rdm=getRandomNumber(0,3);
			
		
	
			if(rdm==0&&isOk(a,0,a.getAgility())) {
				//a.getPosition().setColumn(a.getPosition().getColumn()+a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine(),a.getPosition().getColumn()+a.getAgility()));
				hasmoved=true;
			}
			else if(rdm==1&&isOk(a,1,a.getAgility())) {
				//a.getPosition().setColumn(a.getPosition().getColumn()-a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine(),a.getPosition().getColumn()-a.getAgility()));

				hasmoved=true;
			}
			
			else if(rdm==2&&isOk(a,2,a.getAgility())) {
				//a.getPosition().setLine(a.getPosition().getLine()+a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine()+a.getAgility(),a.getPosition().getColumn()));

				hasmoved=true;
			}
			else if(rdm==3&&isOk(a,3,a.getAgility())) {
				
				//a.getPosition().setLine(a.getPosition().getLine()-a.getAgility());
				a.setPosition(new Block(a.getPosition().getLine()-a.getAgility(),a.getPosition().getColumn()));

				hasmoved=true;
			}
		}

	
}
	
	

	/**
	 * check if the move to a block is accessible
	 * @param i The intruder who move 
	 * @param mvt The direction he will take
	 * @param deplacement The number of case we want to move 
	 * @return true if the block is accessible, false else 
	 */	
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
	
	

	/**
	 * check if there is intruders on the map
	 * @return true if there aren't intruders on the map, false else
	 */	
	public boolean intrudervoid() {
		return intruders.size()==0;
	}
	
	/**
	 * check if intruder can exit thanks to conditions. Move on the exit gate if it's possible 
	 */	
	public void goExit() {
		boolean exit=false;

		for(Intruder intruder: intruders) {
			List<Block> ivision=intruder.getVisionzone();
			for(Block b:ivision) {
				if((b.equals(gate.getPosition())&&totalmoney>=1000)||(b.equals(gate.getPosition())&&guardians.size()>3)) {
					exit=true;
					Block gateposition=gate.getPosition();
					int gateline=gateposition.getLine();
					int gatecolumn=gateposition.getColumn();
					int intruderline=intruder.getPosition().getLine();
					int intrudercolumn=intruder.getPosition().getColumn();
					if(gateline<intruderline) {
						if(intruderline-gateline==1) {
							deplacement(intruder,3,1);
						}
						else {
							deplacement(intruder,3,intruder.getAgility());
						}
					}
					else if(gateline>intruderline){
						if(gateline-intruderline==1) {
							deplacement(intruder,2,1);
						}
						else {
							deplacement(intruder,2,intruder.getAgility());
						}			
					}
					else if(gatecolumn>intrudercolumn) {
						if(gatecolumn-intrudercolumn==1) {
							deplacement(intruder,0,1);
						}
						else {
							deplacement(intruder,0,intruder.getAgility());
						}
					}
					else if(gatecolumn<intrudercolumn){
						if(intrudercolumn-gatecolumn==1) {
							deplacement(intruder,1,1);
						}
						else {
							deplacement(intruder,1,intruder.getAgility());
						}
					}

				}
			}
			
		}
		if(exit) {
			exitintruders();
		}
	}

	/**
	 * exit intruders on the exit gate if the required conditions are fulfilled
	 */	
	public void exitintruders() {
		List<Intruder> eliminatedintruders = new ArrayList<Intruder>();
		for (Intruder intruder : intruders) {
			Block intruderPosition = intruder.getPosition();
			if(gate.getPosition().equals(intruderPosition)) {
				exitmoney+=intruder.moneyearned;
				eliminatedintruders.add(intruder);

			}
		}
		for(Intruder intruder:eliminatedintruders) {
			intruders.remove(intruder);
		}

	}
	
	/**
	 * increment the value total money every round
	 */	
	public void moneyearned() {
		totalmoney+=roundmoney;
		roundmoney=0;
		System.out.println("totalmoney : "+totalmoney);
		System.out.println("exitmoney : "+exitmoney);

		

	}
	
	/**
	 * check if a guardian is in the guardianzone of the Guardian a
	 * @param a a Guardian
	 * @return true if there is a guardian in the visionzone of a, false else.
	 */
	public boolean isInGVision(Guardian a) {
		for(Block b:a.getVisionzone()) {
			for(Guardian g: guardians) {
				if(b.equals(g.getPosition())) {
					return true;
				}
			}
		}
		return false;
		
	}
	



	


	 /**
	 * get the map
	 * @return a map
	 */
	public Map getMap() {
		return map;
	}

	
	 /**
	 * set the map
	 * @param map map 
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	 /**
	 * get a list of items
	 * @return a list of items
	 */
	public List<Item> getItems() {
		return items;
	}

	 /**
	 * set a list of items
	 * @param items list of items
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	 /**
	 * get a list of guardians
	 * @return a list of guardians
	 */
	public List<Guardian> getGuardians() {
		return guardians;
	}

	 /**
	 * set a list of guardians
	 * @param guardians list of guardians
	 */
	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	 /**
	 * get a list of intruders
	 * @return a list of intruders
	 */
	public List<Intruder> getIntruders() {
		return intruders;
	}

	 /**
	 * set a list of intruders
	 * @param intruders list of intruders
	 */
	public void setIntruders(List<Intruder> intruders) {
		this.intruders = intruders;
	}
	

	 /**
	 * get the exit gate
	 * @return the exit gate
	 */
	public ExitGate getGate() {
		return gate;
	}

	 /**
	 * set an exit gate
	 * @param gate a gate
	 */
	public void setGate(ExitGate gate) {
		this.gate = gate;
	}

	 /**
	 * get the lure dropped by the intruder
	 * @return the lure dropped by the intruder
	 */
	public Item getMlure() {
		return mlure;
	}




	 /**
	 * set a lure dropped by the intruder
	 * @param mlure a lure dropped by the intruder
	 */
	public void setMlure(Item mlure) {
		this.mlure = mlure;
	}
	
	 /**
	 * get the net dropped by the guardian
	 * @return the net dropped by the guardian
	 */
	public Item getMfilet() {
		return mfilet;
	}




	 /**
	 * get the number of actual round
	 * @return the number of actual round
	 */
	public int getRound() {
		return round;
	}




	 /**
	 * set the number of the actual round
	 * @param round the number of the actual round 
	 */
	public void setRound(int round) {
		this.round = round;
	}




	 /**
	 * set a net dropped by the guardian
	 * @param mfilet a net dropped by the guardian
	 */
	public void setMfilet(Item mfilet) {
		this.mfilet = mfilet;
	}
	
	 /**
	 * get the value of actual total money
	 * @return the value of actual total money
	 */
	public int getTotalmoney() {
		return totalmoney;
	}




	 /**
	 * set the value of the actual total money
	 * @param totalmoney the value of the actual total money
	 */
	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	






	 /**
	 * get the whistle of the guardian
	 * @return the whistle of the guardian
	 */
	public Item getSiflet() {
		return msifflet;
	}




	 /**
	 * set the whistle of the guardian
	 * @param msiflet the whistle of the guardian
	 */
	public void setSiflet(Item msiflet) {
		this.msifflet = msiflet;
	}




	 /**
	 * get the value of the money taken out by the intruders
	 * @return the money taken out by the intruders
	 */
	public int getExitmoney() {
		return exitmoney;
	}




	 /**
	 * set the value of the money taken out by the intruders
	 * @param exitmoney the money taken out by the intruders
	 */
	public void setExitmoney(int exitmoney) {
		this.exitmoney = exitmoney;
	}




	 /**
	 * get the number of duel
	 * @return the number of duel
	 */
	public int getDuels() {
		return duels;
	}




	 /**
	 * set the number of duel
	 * @param duels the number of duel
	 */
	public void setDuels(int duels) {
		this.duels = duels;
	}




	 /**
	 * get the number of arrest
	 * @return the number of arrest
	 */
	public int getArrestations() {
		return arrestations;
	}




	 /**
	 * set the number of arrest
	 * @param arrestations the number of arrest
	 */
	public void setArrestations(int arrestations) {
		this.arrestations = arrestations;
	}




	 /**
	 * get fire alarm sound
	 * @return the fire alarm sound
	 */
	public Sound getIncendie() {
		return incendie;
	}




	 /**
	 * set fire alarm sound
	 * @param incendie the fire alarm sound
	 */
	public void setIncendie(Sound incendie) {
		this.incendie = incendie;
	}




	 /**
	 * pick a random number between a min and a max
	 * @param min The lower bound
	 * @param max The upper bound
	 * @return a random number
	 */
	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}






}
