package test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import config.GameConfiguration;
import map.Block;
import map.Map;
import model.Guardian;
import model.Intruder;
import model.Item;
import process.GameBuilder;
import process.MobileElementManager;

public class TestUnitIntruder {
	private Intruder intruder;
	private Guardian guardian;
	
	public TestUnitIntruder() {
		Block block=new Block(2,2);
		this.intruder=new Intruder(block);
		this.guardian=new Guardian(block);

		
	}
	
	@Before
	public void prepareIntruders() {
		intruder.inititem();
		guardian.inititem();
	}
	
	
	@Test
	public void testPrecision() {
		int visioni=intruder.getDodge();
		int visiong=guardian.getPrecision();

		assertTrue(visioni<=visiong);
	}
}
