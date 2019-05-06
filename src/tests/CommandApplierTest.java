package tests;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contract.algorithms.CommandAccepterContract;
import contract.algorithms.CommandApplierContract;
import contract.contracterr.PreconditionError;
import model.algorithms.GuardCommandAccepter;
import model.algorithms.GuardCommandApplier;
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

public class CommandApplierTest {
	private CommandApplierContract<Guard, GuardCommandType> commandApplier;
	IGameState state1;
	IGameState state2;
	IGameState state3;
	IGameState state4;
	IGameState state5;
	private int width = 20;
	private int height = 10;
	
	@Before
	public void beforeTests() {
		commandApplier = new CommandApplierContract(new GuardCommandApplier());
		
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
		commandApplier = null;
		state1 = null;
		state2 = null;
		state3 = null;
		state4 = null;
		state5 = null;
	}
	
	
	@Test
	public void applyTest1() {
		try{
			commandApplier.apply(GuardCommandType.LEFT, (Guard) state1.getPool().getGuards().get(0));
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try{
			commandApplier.apply(GuardCommandType.RIGHT, (Guard) state1.getPool().getGuards().get(0));
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try{
			commandApplier.apply(GuardCommandType.UP, (Guard) state1.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
		
	}
	
	@Test
	public void applyTest2() {
		try{
			commandApplier.apply(GuardCommandType.LEFT, (Guard) state2.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
		
		try{
			commandApplier.apply(GuardCommandType.RIGHT, (Guard) state2.getPool().getGuards().get(0));
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try{
			commandApplier.apply(GuardCommandType.UP, (Guard) state2.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
		
	}
	
	@Test
	public void applyTest3() {
		try{
			commandApplier.apply(GuardCommandType.UP, (Guard) state3.getPool().getGuards().get(0));
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try{
			commandApplier.apply(GuardCommandType.RIGHT, (Guard) state3.getPool().getGuards().get(0));
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try{
			commandApplier.apply(GuardCommandType.RIGHT, (Guard) state3.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void applyTest4() {
		try{
			commandApplier.apply(GuardCommandType.UP, (Guard) state4.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
		
		try{
			commandApplier.apply(GuardCommandType.RIGHT, (Guard) state4.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
		
		try{
			commandApplier.apply(GuardCommandType.RIGHT, (Guard) state4.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
	
	@Test
	public void applyTest5() {
		try{
			commandApplier.apply(GuardCommandType.LEFT, (Guard) state5.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
		
		try{
			commandApplier.apply(GuardCommandType.DOWN, (Guard) state5.getPool().getGuards().get(0));
			assertTrue(true);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		try{
			commandApplier.apply(GuardCommandType.UP, (Guard) state5.getPool().getGuards().get(0));
			assertTrue(false);
		}catch(PreconditionError e) {
			e.printStackTrace();
			assertTrue(true);
		}
	}
}