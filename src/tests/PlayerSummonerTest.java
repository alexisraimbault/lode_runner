package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import contract.entities.PlayerSummonerContract;
import model.gamestate.GameState;
import model.gamestate.entities.TreasureSummoner;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.EntityType;
import model.services.IEnvironment;
import model.services.IGameState;
import model.services.Nature;

public class PlayerSummonerTest  {
	PlayerSummonerContract playerContract1;
	PlayerSummonerContract playerContract2;
	PlayerSummonerContract playerContract3;
	
	IGameState state;
	IGameState state2;
	IGameState state3;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		int nb_lives = 3;
		
		EditableEnvironment env = new EditableEnvironment(new DynamicScreen());
		env.resize(width, height);
		for(int i = 0; i<width; i++){
			env.setCellNature(i, 0, Nature.METAL);
		}
		env.getCellContent(1, 1).add(EntityType.PLAYER);
		env.getCellContent(1, 1).add(EntityType.TREASURE);
		IEnvironment envProduced = new Environment(env.produce());
		state = new GameState(envProduced, OperationsSpeeds.default_speeds, nb_lives);
		playerContract1 = new PlayerSummonerContract(state.getPool().getPlayerSummoner());
		
		
		EditableEnvironment env2 = new EditableEnvironment(new DynamicScreen());
		env2.resize(width, height);
		for(int i = 0; i<width; i++){
			env2.setCellNature(i, 0, Nature.METAL);
		}
		env2.getCellContent(1, 1).add(EntityType.PLAYER);
		env2.getCellContent(3, 1).add(EntityType.TREASURE);
		IEnvironment envProduced2 = new Environment(env2.produce());
		state2 = new GameState(envProduced2, OperationsSpeeds.default_speeds, nb_lives);
		playerContract2 = new PlayerSummonerContract(state2.getPool().getPlayerSummoner());
		
		EditableEnvironment env3 = new EditableEnvironment(new DynamicScreen());
		env3.resize(width, height);
		for(int i = 0; i<width; i++){
			env3.setCellNature(i, 0, Nature.METAL);
		}
		env3.getCellContent(1, height - 1).add(EntityType.PLAYER);
		env3.getCellContent(1, height - 1).add(EntityType.TREASURE);
		IEnvironment envProduced3 = new Environment(env3.produce());
		state3 = new GameState(envProduced3, OperationsSpeeds.default_speeds, nb_lives);
		playerContract3 = new PlayerSummonerContract(state3.getPool().getPlayerSummoner());
		
	}
	
	@After
	public final void afterTests() {
		playerContract1 = null;
		playerContract2 = null;
		playerContract3 = null;
		state = null;
		state2 = null;
		state3 = null;
	}
	
	@Test
	public void getTreasureScoreTest() {
		assertTrue(playerContract1.getTreasureScore() == 0);
	}
	
	@Test
	public void getCoinScoreTest() {
		assertTrue(playerContract1.getCoinScore() == 0);
	}
	
	@Test
	public void getNbLivesTest() {
		assertTrue(playerContract1.getNbLives() == 3);
	}
	
	@Test
	public void canCollectTest1() {
		TreasureSummoner treasure = new TreasureSummoner(state.getPool().getTreasureSummoners().get(0).getInstance());
		assertTrue(playerContract1.canCollect(treasure) == true);
	}
	
	@Test
	public void canCollectTest2() {
		TreasureSummoner treasure = new TreasureSummoner(state2.getPool().getTreasureSummoners().get(0).getInstance());
		assertTrue(playerContract2.canCollect(treasure) == false);
	}
	
	@Test
	public void canCollectTestKONoItemInstance() {
		try{
			playerContract2.canCollect(new TreasureSummoner(null));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void collectTestOK() {
		TreasureSummoner treasure = new TreasureSummoner(state.getPool().getTreasureSummoners().get(0).getInstance());
		try{
			playerContract1.collect(treasure);
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void collectTestKO() {
		TreasureSummoner treasure = new TreasureSummoner(state2.getPool().getTreasureSummoners().get(0).getInstance());
		try{
			playerContract2.collect(treasure);
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void respawnTest1() {
		try{
			playerContract1.respawn();
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void respawnTest2() {
		try{
			playerContract2.respawn();
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void winsTestKO1() {//score not high enough
		try{
			playerContract2.wins(1);
			assertTrue(false);
		}catch(PostconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void winsTestKO2() {//player not in top of the map
		TreasureSummoner treasure = new TreasureSummoner(state.getPool().getTreasureSummoners().get(0).getInstance());
		playerContract1.collect(treasure);
		try{
			playerContract1.wins(1);
			assertTrue(false);
		}catch(PostconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void winsTestOK() {
		TreasureSummoner treasure = new TreasureSummoner(state3.getPool().getTreasureSummoners().get(0).getInstance());
		playerContract3.collect(treasure);
		try{
			assertTrue(playerContract3.wins(1));
			assertTrue(true);
		}catch(PostconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
}
