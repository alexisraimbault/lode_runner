package tests;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.algorithms.CommandAccepterContract;
import model.algorithms.GuardCommandAccepter;
import model.gamestate.GameState;
import model.gamestate.entities.Guard;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.EntityType;
import model.services.GuardCommandType;
import model.services.IGameState;
import model.services.Nature;

public class CommandAccepterTest {
	private CommandAccepterContract<Guard, GuardCommandType> commandAccepter;
	IGameState state1;
	IGameState state2;
	IGameState state3;
	IGameState state4;
	IGameState state5;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		commandAccepter = new CommandAccepterContract(new GuardCommandAccepter());
		
		int nb_lives = 3;
		
		EditableEnvironment env1 = new EditableEnvironment(new DynamicScreen());//on a plain ground
		env1.resize(width, height);
		for(int i = 0; i<width; i++){
			env1.setCellNature(i, 0, Nature.METAL);
		}
		env1.getCellContent(8, 2).add(EntityType.PLAYER);
		env1.getCellContent(5, 1).add(EntityType.GUARD);
		env1.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced1 = new Environment(env1.produce());
		state1 = new GameState(envProduced1, OperationsSpeeds.default_speeds, nb_lives);
		
		
		EditableEnvironment env2 = new EditableEnvironment(new DynamicScreen());//against a wall
		env2.resize(width, height);
		for(int i = 0; i<width; i++){
			env2.setCellNature(i, 0, Nature.METAL);
		}
		env2.getCellContent(8, 2).add(EntityType.PLAYER);
		env2.getCellContent(5, 1).add(EntityType.GUARD);
		env2.setCellNature(4, 1, Nature.PLATFORM);
		env2.setCellNature(4, 2, Nature.PLATFORM);
		env2.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced2 = new Environment(env2.produce());
		state2 = new GameState(envProduced2, OperationsSpeeds.default_speeds, nb_lives);
		
		EditableEnvironment env3 = new EditableEnvironment(new DynamicScreen());
		env3.resize(width, height);
		for(int i = 0; i<width; i++){
			env3.setCellNature(i, 0, Nature.METAL);
		}
		env3.getCellContent(8, 2).add(EntityType.PLAYER);
		env3.getCellContent(5, 2).add(EntityType.GUARD);//On a ladder
		env3.setCellNature(5, 1, Nature.LADDER);
		env3.setCellNature(5, 2, Nature.LADDER);
		env3.setCellNature(5, 3, Nature.LADDER);
		env3.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced3 = new Environment(env3.produce());
		state3 = new GameState(envProduced3, OperationsSpeeds.default_speeds, nb_lives);
		
		EditableEnvironment env4 = new EditableEnvironment(new DynamicScreen());//between 4 walls
		env4.resize(width, height);
		for(int i = 0; i<width; i++){
			env4.setCellNature(i, 0, Nature.METAL);
		}
		env4.getCellContent(8, 2).add(EntityType.PLAYER);
		env4.setCellNature(4, 1, Nature.PLATFORM);
		env4.setCellNature(6, 1, Nature.PLATFORM);
		env4.setCellNature(5, 2, Nature.PLATFORM);
		env4.getCellContent(5, 1).add(EntityType.GUARD);
		env4.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced4 = new Environment(env4.produce());
		state4 = new GameState(envProduced4, OperationsSpeeds.default_speeds, nb_lives);
		
		EditableEnvironment env5 = new EditableEnvironment(new DynamicScreen());//flying
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
		Set<GuardCommandType> acceptedMoves = commandAccepter.accept((Guard) state1.getPool().getGuards().get(0));
		assertTrue(acceptedMoves.contains(GuardCommandType.LEFT));
		assertTrue(acceptedMoves.contains(GuardCommandType.RIGHT));
		assertTrue(acceptedMoves.size() == 2);
	}
	
	@Test
	public void acceptTest2() {
		Set<GuardCommandType> acceptedMoves = commandAccepter.accept((Guard) state2.getPool().getGuards().get(0));
		assertTrue(acceptedMoves.contains(GuardCommandType.RIGHT));
		assertTrue(acceptedMoves.size() == 1);
	}
	
	@Test
	public void acceptTest3() {
		Set<GuardCommandType> acceptedMoves = commandAccepter.accept((Guard) state3.getPool().getGuards().get(0));
		assertTrue(acceptedMoves.contains(GuardCommandType.LEFT));
		assertTrue(acceptedMoves.contains(GuardCommandType.RIGHT));
		assertTrue(acceptedMoves.contains(GuardCommandType.UP));
		assertTrue(acceptedMoves.contains(GuardCommandType.DOWN));
		assertTrue(acceptedMoves.size() == 4);
	}
	
	@Test
	public void acceptTest4() {
		Set<GuardCommandType> acceptedMoves = commandAccepter.accept((Guard) state4.getPool().getGuards().get(0));
		assertTrue(acceptedMoves.size() == 0);
	}
	
	@Test
	public void acceptTest5() {
		Set<GuardCommandType> acceptedMoves = commandAccepter.accept((Guard) state5.getPool().getGuards().get(0));
		assertTrue(acceptedMoves.contains(GuardCommandType.DOWN));
		assertTrue(acceptedMoves.size() == 1);
	}
}
