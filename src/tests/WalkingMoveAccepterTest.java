package tests;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.algorithms.FallingMoveAccepterContract;
import contract.algorithms.WalkingMoveAccepterContract;
import model.algorithms.FallingMoveAccepter;
import model.algorithms.PlentyAndCharacterTester;
import model.algorithms.WalkingMoveAccepter;
import model.gamestate.GameState;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.EntityType;
import model.services.IGameState;
import model.services.IPlayer;
import model.services.MoveType;
import model.services.Nature;

public class WalkingMoveAccepterTest  {
	private WalkingMoveAccepterContract<IPlayer> commandAccepter;
	IGameState state1;
	IGameState state2;
	IGameState state3;
	IGameState state4;
	IGameState state5;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		commandAccepter = new WalkingMoveAccepterContract(new WalkingMoveAccepter<IPlayer>(new PlentyAndCharacterTester()));
		
		int nb_lives = 3;
		
		EditableEnvironment env1 = new EditableEnvironment(new DynamicScreen());//On the ground
		env1.resize(width, height);
		for(int i = 0; i<width; i++){
			env1.setCellNature(i, 0, Nature.METAL);
		}
		env1.getCellContent(8, 1).add(EntityType.PLAYER);
		env1.getCellContent(5, 1).add(EntityType.GUARD);
		env1.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced1 = new Environment(env1.produce());
		state1 = new GameState(envProduced1, OperationsSpeeds.default_speeds, nb_lives);
		
		
		EditableEnvironment env5 = new EditableEnvironment(new DynamicScreen());//between a guard and a wall
		env5.resize(width, height);
		for(int i = 0; i<width; i++){
			env5.setCellNature(i, 0, Nature.METAL);
		}
		env5.getCellContent(8, 2).add(EntityType.PLAYER);
		env5.getCellContent(5, 5).add(EntityType.GUARD);
		env5.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced5 = new Environment(env5.produce());
		state5 = new GameState(envProduced5, OperationsSpeeds.default_speeds, nb_lives);
		
	}
	
	@After
	public final void afterTests() {
		commandAccepter = null;
		state1 = null;
		state2 = null;
		state3 = null;
		state4 = null;
		state5 = null;
	}
	
	
	@Test
	public void acceptTest1() {
		Set<MoveType> acceptedMoves = commandAccepter.accept( state1.getPool().getPlayer());
		assertTrue(acceptedMoves.contains(MoveType.LEFT));
		assertTrue(acceptedMoves.contains(MoveType.RIGHT));
		assertTrue(acceptedMoves.size() == 2);
	}
	
	
	@Test
	public void acceptTest5() {
		Set<MoveType> acceptedMoves = commandAccepter.accept( state5.getPool().getPlayer());
		assertTrue(acceptedMoves.size() == 0);
	}
}