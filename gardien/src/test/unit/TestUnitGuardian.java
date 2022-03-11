package test.unit;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import map.Block;
import model.Guardian;
import model.Item;

public class TestUnitGuardian {
	private Guardian guardian;
	
	public TestUnitGuardian() {
		Block block=new Block(2,2);
		this.guardian=new Guardian(block);
	}
	
	@Before
	public void prepareGuardian() {
		guardian.inititem();
	}
	
	
	@Test
	public void testFilet() {
		HashMap<String, Item> items=guardian.getItems();
		assertEquals(2,items.get("Filet").getNbre());
	}
}
