package test.unit;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;



import org.junit.Before;
import org.junit.Test;

import map.Block;
import model.Guardian;
import model.Item;


/**
 * Unit test of guardian initialization. Checks if the guardian's initial number of nets is 2
 * 
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class TestUnitGuardian {
	private Guardian guardian;
	
	public TestUnitGuardian() {
		Block block=new Block(2,2);
		this.guardian=new Guardian(block);
	}
	/**
	 * Initializes the guardian with its initial attributes 
	 */
	@Before
	public void prepareGuardian() {
		guardian.inititem();
	}
	
	/**
	 * Test the quantity if net of the guardian
	 */
	@Test
	public void testFilet() {
		HashMap<String, Item> items=guardian.getItems();
		assertEquals(2,items.get("Filet").getNbre());
	}
}
