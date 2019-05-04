package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.contracterr.PreconditionError;
import contract.environment.EnvironmentContract;
import model.gamestate.environment.DynamicEnvironment;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.Environment;

public class EnvironmentTest {
	private EnvironmentContract testedEnv;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		DynamicEnvironment dEnv = new DynamicEnvironment(new DynamicScreen());
		dEnv.resize(width, height);
		testedEnv = new  EnvironmentContract(new Environment(dEnv));
		
	}
	
	@After
	public final void afterTests() {
		testedEnv = null;
	}
	
	@Test
	public void testGetCellContentOK1() {
		try{
			testedEnv.getCellContent(0, 0);
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	@Test
	public void testGetCellContentOK2() {
		try{
			testedEnv.getCellContent(5, height-1);
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	@Test
	public void testGetCellContentOK3() {
		try{
			testedEnv.getCellContent(width - 1, 5);
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetCellContentKO1() {
		try{
			testedEnv.getCellContent(-1, 0);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	@Test
	public void testGetCellContentKO2() {
		try{
			testedEnv.getCellContent(0, -1);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	@Test
	public void testGetCellContentKO3() {
		try{
			testedEnv.getCellContent(0, height+1);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
}
