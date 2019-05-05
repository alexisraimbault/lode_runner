package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.contracterr.PreconditionError;
import contract.environment.ScreenContract;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableScreen;
import model.gamestate.environment.Screen;
import model.services.Nature;

public class ScreenTest {
	ScreenContract testedScreen1;
	ScreenContract testedScreen2;
	ScreenContract testedScreen3;
	private int width = 20;
	private int height = 10;
	int x = 1, y = 1;
	
	@Before
	public void beforeTests() {
		EditableScreen editableScreen1 = new EditableScreen(new DynamicScreen());
		editableScreen1.resize(width, height);
		testedScreen1 = new  ScreenContract(new Screen(editableScreen1));
		
		EditableScreen editableScreen2 = new EditableScreen(new DynamicScreen());
		editableScreen2.resize(width, height);
		editableScreen2.setCellNature(x, y, Nature.HOLE);
		testedScreen2 = new  ScreenContract(new Screen(editableScreen2));
		
		EditableScreen editableScreen3 = new EditableScreen(new DynamicScreen());
		editableScreen3.resize(width, height);
		editableScreen3.setCellNature(x, y, Nature.PLATFORM);
		testedScreen3 = new  ScreenContract(new Screen(editableScreen3));
	}
		
	
	
	@After
	public final void afterTests() {
		testedScreen1 = null;
		testedScreen2 = null;
		testedScreen3 = null;
	}
	
	@Test
	public void getCellNatureTestOK1() {
		assertTrue(testedScreen3.getCellNature(x,y) == Nature.PLATFORM);
	}
	
	@Test
	public void getCellNatureTestOK2() {
		assertTrue(testedScreen1.getCellNature(0,height - 1) == Nature.EMPTY);
	}
	
	@Test
	public void getCellNatureTestOK3() {
		assertTrue(testedScreen1.getCellNature(width - 1,0) == Nature.EMPTY);
	}
	
	@Test
	public void getCellNatureTestKO1() {
		try{
			testedScreen1.getCellNature(-1 , 0);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void getCellNatureTestKO2() {
		try{
			testedScreen1.getCellNature(0 , -1);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void getCellNatureTestKO3() {
		try{
			testedScreen1.getCellNature(0 , height);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void getCellNatureTestKO4() {
		try{
			testedScreen1.getCellNature(width , 0);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void digTestOK() {
		try{
			testedScreen3.dig(x,y);
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void digTestKO1() {
		try{
			testedScreen1.dig(x,y);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void digTestKO2() {
		try{
			testedScreen2.dig(x,y);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void fillTestOK() {
		try{
			testedScreen2.fill(x,y);
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void fillTestKO1() {
		try{
			testedScreen1.fill(x,y);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void fillTestKO2() {
		try{
			testedScreen3.fill(x,y);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
}
