package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.gamestate.environment.Content;
import model.services.EntityType;

public class ContentTest {
	private Content testedCont;
	
	@Before
	public void beforeTests() {
		testedCont = new Content();
	}
	
	@After
	public final void afterTests() {
		testedCont = null;
	}
	
	//ADD, REMOVE
	
	@Test
	public void testAdd1() {
		testedCont.add(EntityType.PLAYER);
		assertTrue(testedCont.contains(EntityType.PLAYER) && testedCont.nbCharacters() == 1);
	}
	
	@Test
	public void testAdd2() {
		testedCont.add(EntityType.GUARD);
		assertTrue(testedCont.contains(EntityType.GUARD) && testedCont.nbCharacters() == 1);
	}
	
	@Test
	public void testAdd3() {
		testedCont.add(EntityType.GUARD, 2);
		assertTrue(testedCont.contains(EntityType.GUARD) && testedCont.nbCharacters() == 2);
	}
	
	@Test
	public void testAdd4() {
		testedCont.add(EntityType.PLAYER, 2);
		assertTrue(testedCont.contains(EntityType.PLAYER) && testedCont.nbCharacters() == 2);
	}
	
	@Test
	public void testAdd5() {
		testedCont.add(EntityType.PLAYER);
		testedCont.add(EntityType.GUARD);
		assertTrue(testedCont.contains(EntityType.PLAYER) && testedCont.nbCharacters() == 2 && testedCont.contains(EntityType.GUARD));
	}
	
	@Test
	public void testRemove1() {
		testedCont.add(EntityType.PLAYER);
		testedCont.remove(EntityType.PLAYER);
		assertTrue( testedCont.nbCharacters() == 0);
	}
	
	@Test
	public void testRemove2() {
		testedCont.add(EntityType.PLAYER, 2);
		testedCont.remove(EntityType.PLAYER);
		assertTrue( testedCont.nbCharacters() == 1);
	}
	
	@Test
	public void testRemove3() {
		testedCont.add(EntityType.PLAYER, 2);
		testedCont.remove(EntityType.PLAYER, 2);
		assertTrue( testedCont.nbCharacters() == 0);
	}
	
	@Test
	public void testRemove4() {
		testedCont.add(EntityType.PLAYER, 2);
		testedCont.remove(EntityType.PLAYER);
		testedCont.remove(EntityType.PLAYER);
		assertTrue( testedCont.nbCharacters() == 0);
	}
	
	@Test
	public void testRemove5() {
		testedCont.add(EntityType.PLAYER);
		testedCont.remove(EntityType.PLAYER);
		testedCont.remove(EntityType.PLAYER);
		assertTrue( testedCont.nbCharacters() == 0);
	}
	
}
