package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.HumanPlayerEngine;
import model.gamestate.GameState;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.EntityType;
import model.services.IGameState;
import model.services.Nature;
import model.services.Status;

public class EngineTest  {
	private HumanPlayerEngine engine;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		EditableEnvironment env = new EditableEnvironment(new DynamicScreen());
		env.resize(width, height);
		for(int i = 0; i<width; i++){
			env.setCellNature(i, 0, Nature.METAL);
		}
		env.getCellContent(0, 1).add(EntityType.PLAYER);
		env.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced = new Environment(env.produce());
		final int nb_lives = 3;
		IGameState state = new GameState(envProduced, OperationsSpeeds.default_speeds, nb_lives);
		engine = new HumanPlayerEngine(state);
	}
	
	@After
	public final void afterTests() {
		engine = null;
	}
	
	//START, STOP
	
	@Test
	public void init() {
		assertTrue(engine.getStatus() == Status.PAUSE);
	}
	
	@Test
	public void start1() {
		engine.start();
		assertTrue(engine.getStatus() == Status.PLAYING);
	}
	
	@Test
	public void start2() {
		engine.stop();
		engine.start();
		assertTrue(engine.getStatus() == Status.PLAYING);
	}
	
	@Test
	public void stop1() {
		engine.stop();
		assertTrue(engine.getStatus() == Status.PAUSE);
	}
	
	@Test
	public void stop2() {
		engine.start();
		engine.stop();
		assertTrue(engine.getStatus() == Status.PAUSE);
	}
}
