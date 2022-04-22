package test.unit;


import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import map.Block;
import model.Guardian;
import model.Intruder;
/**
 * Unit test of intruder and guardian initialization. Checks if the guardian's initial precision is greater or equal than the intruder's dodge
 * 
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class TestUnitIntruder {
	private Intruder intruder;
	private Guardian guardian;
	
	public TestUnitIntruder() {
		Block block=new Block(2,2);
		this.intruder=new Intruder(block);
		this.guardian=new Guardian(block);

		
	}
	
	/**
	 * Initializes a guardian and an intruders with their initial attributes 
	 */
	@Before
	public void prepareIntruders() {
		intruder.inititem();
		guardian.inititem();
	}
	
	/**
	 * Test that precision of guardian is always better than to that of the intruder
	 */
	@Test
	public void testPrecision() {
		int dodgei=intruder.getDodge();
		int precisiong=guardian.getPrecision();

		assertTrue(dodgei<=precisiong);
	}
}
