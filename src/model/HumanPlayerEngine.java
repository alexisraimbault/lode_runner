package model;

import java.util.List;

import model.algorithms.GuardMover;
import model.algorithms.PlayerCommandAccepter;
import model.algorithms.PlayerDigger;
import model.algorithms.PlayerMover;
import model.algorithms.RandomGuardDecision;
import model.gamestate.entities.Player;
import model.algorithms.AStarCalculator;
import model.algorithms.CharacterMoverBase;
import model.algorithms.GuardClimber;
import model.algorithms.GuardCommandAccepter;
import model.services.GuardCommandType;
import model.services.IEntityPool;
import model.services.IEnvironment;
import model.services.IGameState;
import model.services.IGuard;
import model.services.IGuardClimber;
import model.services.IGuardDecision;
import model.services.IGuardMover;
import model.services.IHumanPlayerEngine;
import model.services.IOperationsSpeeds;
import model.services.IPlayer;
import model.services.IPlayerDigger;
import model.services.IPlayerMover;
import model.services.IShortestPathCalculator;
import model.services.PlayerCommandType;
import model.services.Status;

public class HumanPlayerEngine implements IHumanPlayerEngine
{
	private IGameState state;
	private IOperationsSpeeds speeds;
	private Status status;
	
	private PlayerCommandType player_command;
	
	private IPlayerMover player_mover;
	private IPlayerDigger player_digger;
	
	private IGuardMover guard_mover;
	private IGuardClimber guard_climber;
	
	//private IShortestPathsCalculator paths_calculator;
	
	public HumanPlayerEngine(IGameState state, IOperationsSpeeds speeds)
	{
		this.state = state;
		this.speeds = speeds;
		this.status = Status.PAUSE;
		
		this.player_command = PlayerCommandType.NEUTRAL;
		
		this.player_mover = new PlayerMover();
		this.player_digger = new PlayerDigger();
		
		this.guard_mover = new GuardMover();
		this.guard_climber = new GuardClimber();
		
		//this.paths_calculator = new AStarCalculator();
	}
	
	@Override
	public IGameState getState()
	{
		return state;
	}

	@Override
	public void stepPlayer()
	{
		IEntityPool pool = state.getPool();
		IPlayer player = pool.getPlayer();
		
		System.out.println("Player : " + (new PlayerCommandAccepter()).accept(player));

		if(player_command.isMoveType())
			player_mover.move(player_command.moveType(), player);
		if(player_command.isDigType())
			player_digger.dig(player_command.digType(), player);
		
		player_command = PlayerCommandType.NEUTRAL;
	}
	
	@Override
	public void stepGuards()
	{
		IEnvironment environment = state.getEnvironment();
		IEntityPool pool = state.getPool();
		List<IGuard> guards = pool.getGuards();
		IPlayer player = pool.getPlayer();
		
		int gi = 0;
		for(IGuard guard : guards)
		{
			IGuardDecision decision = new RandomGuardDecision(new GuardCommandAccepter());
			
			GuardCommandType guard_command = decision.getCommand(guard);
			
			System.out.println("Guard + " + gi + " : " + (new GuardCommandAccepter()).accept(guard));
			
			if(guard_command.isMoveType())
				guard_mover.move(guard_command.moveType(), guard);
			if(guard_command.isClimbType())
				guard_climber.climb(guard_command.digType(), guard);
			
			++gi;
		}
	}
	
	@Override
	public void setCommand(PlayerCommandType command)
	{
		this.player_command = command;
	}

	@Override
	public void start()
	{
		if(status == Status.PAUSE)
			status = Status.PLAYING;
	}

	@Override
	public void stop()
	{
		if(status == Status.PLAYING)
			status = Status.PAUSE;
	}

	@Override
	public Status getStatus()
	{
		return status;
	}

	@Override
	public IOperationsSpeeds getOperationsSpeeds()
	{
		return speeds;
	}

}
