package tests;

import static org.junit.Assert.*;

import org.junit.*;

import contract.contracterr.PreconditionError;
import contract.environment.EditableEnvironmentContract;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.EntityType;
import model.services.Nature;
import model.services.Status;

public class EditableEnvironmentTest {
	private EditableEnvironmentContract testedEnv;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		testedEnv = new  EditableEnvironmentContract(new EditableEnvironment(new DynamicScreen()));
	}
	
	@After
	public final void afterTests() {
		testedEnv = null;
	}
	
	@Test
	public void testResize() {
		testedEnv.resize(width, height);
		assertTrue(testedEnv.getHeight() == height && testedEnv.getWidth() == width);
	}
	
	@Test
	public void testSetCellNature1(){
		testedEnv.resize(width, height);
		testedEnv.setCellNature(0, 0, Nature.METAL);
		assertTrue(testedEnv.getCellNature(0,0) == Nature.METAL);
	}
	
	@Test
	public void testSetCellNature2(){
		testedEnv.resize(width, height);
		testedEnv.setCellNature(5, 2, Nature.PLATFORM);
		assertTrue(testedEnv.getCellNature(5,2) == Nature.PLATFORM);
	}
	
	@Test
	public void testSetCellNature3(){
		testedEnv.resize(width, height);
		testedEnv.setCellNature(2, 5, Nature.LADDER);
		assertTrue(testedEnv.getCellNature(2,5) == Nature.LADDER);
	}
	
	
	@Test
	public void testPlayable1(){
		testedEnv.resize(width, height);
		for(int i = 0; i<width; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(0, 1).add(EntityType.PLAYER);
		testedEnv.getCellContent(9, 1).add(EntityType.TREASURE);
		assertTrue(testedEnv.isPlayable());
	}
	
	@Test
	public void testPlayable2(){//player en l'air
		testedEnv.resize(width, height);
		for(int i = 0; i<width; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(9, 5).add(EntityType.PLAYER);
		testedEnv.getCellContent(0, 1).add(EntityType.TREASURE);
		assertTrue(testedEnv.isPlayable());
	}
	
	@Test
	public void testPlayableFalse1(){//2 players
		testedEnv.resize(width, height);
		for(int i = 0; i<width; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(9, 5).add(EntityType.PLAYER);
		testedEnv.getCellContent(0, 1).add(EntityType.PLAYER);
		testedEnv.getCellContent(0, 1).add(EntityType.TREASURE);
		assertFalse(testedEnv.isPlayable());
	}
	
	@Test
	public void testPlayableFalse2(){//no treasures
		testedEnv.resize(width, height);
		for(int i = 0; i<width; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(0, 1).add(EntityType.PLAYER);
		assertFalse(testedEnv.isPlayable());
	}
	
	@Test
	public void testPlayableFalse3(){//no metal on the bottom layer
		testedEnv.resize(width, height);
		for(int i = 0; i<width - 1; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(0, 1).add(EntityType.PLAYER);
		testedEnv.getCellContent(0, 1).add(EntityType.TREASURE);
		assertFalse(testedEnv.isPlayable());
	}
	
	@Test
	public void testPlayableFalse4(){//treasure not on the ground
		testedEnv.resize(width, height);
		for(int i = 0; i<width ; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(0, 1).add(EntityType.PLAYER);
		testedEnv.getCellContent(0, 2).add(EntityType.TREASURE);
		assertFalse(testedEnv.isPlayable());
	}
	
	@Test
	public void produceKo () {
		testedEnv.resize(width, height);
		try{
			testedEnv.produce();
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void produceOk () {
		testedEnv.resize(width, height);
		for(int i = 0; i<width; i++){
			testedEnv.setCellNature(i, 0, Nature.METAL);
		}
		testedEnv.getCellContent(0, 1).add(EntityType.PLAYER);
		testedEnv.getCellContent(9, 1).add(EntityType.TREASURE);
		try{
			testedEnv.produce();
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
