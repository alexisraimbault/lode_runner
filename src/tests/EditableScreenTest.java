package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.contracterr.PreconditionError;
import contract.environment.EditableScreenContract;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableScreen;
import model.services.Nature;

public class EditableScreenTest {
	private EditableScreenContract testedScreen;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		testedScreen = new EditableScreenContract( new EditableScreen(new DynamicScreen()));
		testedScreen.resize(width, height);
	}
	
	@After
	public final void afterTests() {
		testedScreen = null;
	}
	
	@Test
	public void isPlayableTest1() {
		for(int i = 0; i<width; i++){
			testedScreen.setCellNature(i, 0, Nature.METAL);
		}
		assertTrue(testedScreen.isPlayable());
	}
	
	@Test
	public void isPlayableTest2() {
		assertFalse(testedScreen.isPlayable());
	}
	@Test
	
	public void isPlayableTest3() {
		for(int i = 0; i<width-1; i++){
			testedScreen.setCellNature(i, 0, Nature.METAL);
		}
		assertFalse(testedScreen.isPlayable());
	}
	
	@Test
	public void produceTest1() {
		for(int i = 0; i<width; i++){
			testedScreen.setCellNature(i, 0, Nature.METAL);
		}
		try{
			testedScreen.produce();
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void produceTest2() {
		try{
			testedScreen.produce();
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	@Test
	
	public void produceTest3() {
		for(int i = 0; i<width-1; i++){
			testedScreen.setCellNature(i, 0, Nature.METAL);
		}
		try{
			testedScreen.produce();
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
}
