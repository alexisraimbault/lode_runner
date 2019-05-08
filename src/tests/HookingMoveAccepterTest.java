package tests;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.algorithms.CommandAccepterContract;
import contract.algorithms.HookingMoveAccepterContract;
import model.algorithms.GuardCommandAccepter;
import model.algorithms.HookingMoveAccepter;
import model.gamestate.GameState;
import model.gamestate.entities.Guard;
import model.gamestate.entities.Player;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.EntityType;
import model.services.GuardCommandType;
import model.services.IGameState;
import model.services.IPlayer;
import model.services.MoveType;
import model.services.Nature;
import model.services.PlayerCommandType;

public class HookingMoveAccepterTest {
	private HookingMoveAccepterContract<IPlayer> commandAccepter;
	IGameState state1;
	IGameState state2;
	IGameState state3;
	IGameState state4;
	IGameState state5;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		commandAccepter = new HookingMoveAccepterContract(new HookingMoveAccepter<IPlayer>());
		
		int nb_lives = 3;
		
		EditableEnvironment env1 = new EditableEnvironment(new DynamicScreen());//on a ladder
		env1.resize(width, height);
		for(int i = 0; i<width; i++){
			env1.setCellNature(i, 0, Nature.METAL);
		}
		env1.getCellContent(8, 2).add(EntityType.PLAYER);
		env1.getCellContent(5, 1).add(EntityType.GUARD);
		env1.getCellContent(10, 1).add(EntityType.TREASURE);
		env1.setCellNature(8, 2, Nature.LADDER);
		Environment envProduced1 = new Environment(env1.produce());
		state1 = new GameState(envProduced1, OperationsSpeeds.default_speeds, nb_lives);
		
		
		EditableEnvironment env2 = new EditableEnvironment(new DynamicScreen());//on a handrail
		env2.resize(width, height);
		for(int i = 0; i<width; i++){
			env2.setCellNature(i, 0, Nature.METAL);
		}
		env2.getCellContent(8, 2).add(EntityType.PLAYER);
		env2.getCellContent(5, 1).add(EntityType.GUARD);
		env2.setCellNature(8, 2, Nature.HANDRAIL);
		env2.getCellContent(10, 1).add(EntityType.TREASURE);
		Environment envProduced2 = new Environment(env2.produce());
		state2 = new GameState(envProduced2, OperationsSpeeds.default_speeds, nb_lives);
		
		EditableEnvironment env3 = new EditableEnvironment(new DynamicScreen());//On top of a ladder
		env3.resize(width, height);
		for(int i = 0; i<width; i++){
			env3.setCellNature(i, 0, Nature.METAL);
		}
		env3.getCellContent(8, 2).add(EntityType.PLAYER);
		env3.getCellContent(5, 2).add(EntityType.GUARD);
		env3.setCellNature(8, 1, Nature.LADDER);
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
		Set<MoveType> acceptedMoves = commandAccepter.accept( state1.getPool().getPlayer());
		assertTrue(acceptedMoves.contains(MoveType.LEFT));
		assertTrue(acceptedMoves.contains(MoveType.RIGHT));
		assertTrue(acceptedMoves.contains(MoveType.UP));
		assertTrue(acceptedMoves.contains(MoveType.DOWN));
		assertTrue(acceptedMoves.size() == 4);
	}
	
	@Test
	public void acceptTest2() {
		Set<MoveType> acceptedMoves = commandAccepter.accept( state2.getPool().getPlayer());
		assertTrue(acceptedMoves.contains(MoveType.LEFT));
		assertTrue(acceptedMoves.contains(MoveType.RIGHT));
		assertTrue(acceptedMoves.contains(MoveType.DOWN));
		assertTrue(acceptedMoves.size() == 3);
	}
	
	@Test
	public void acceptTest3() {
		Set<MoveType> acceptedMoves = commandAccepter.accept( state3.getPool().getPlayer());
		assertTrue(acceptedMoves.contains(MoveType.LEFT));
		assertTrue(acceptedMoves.contains(MoveType.RIGHT));
		assertTrue(acceptedMoves.size() == 2);
	}
	
	@Test
	public void acceptTest4() {
		Set<MoveType> acceptedMoves = commandAccepter.accept( state4.getPool().getPlayer());
		assertTrue(acceptedMoves.size() == 0);
	}
	
	@Test
	public void acceptTest5() {
		Set<MoveType> acceptedMoves = commandAccepter.accept( state5.getPool().getPlayer());
		assertTrue(acceptedMoves.size() == 0);
	}
}