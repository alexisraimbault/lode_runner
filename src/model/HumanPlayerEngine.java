package model;

import java.util.List;

import model.algorithms.GuardMover;
import model.algorithms.PlayerCommandAccepter;
import model.algorithms.PlayerDigger;
import model.algorithms.PlayerMover;
import model.algorithms.RandomDecision;
import model.algorithms.AStarCalculator;
import model.algorithms.AStarDecision;
import model.algorithms.GuardClimber;
import model.algorithms.GuardCommandAccepter;
import model.services.GuardCommandType;
import model.services.IAStarDecision;
import model.services.IEntityPool;
import model.services.IGameState;
import model.services.IGuard;
import model.services.IGuardClimber;
import model.services.IGuardCommandAccepter;
import model.services.IGuardMover;
import model.services.IHumanPlayerEngine;
import model.services.IOperationsSpeeds;
import model.services.IPlayer;
import model.services.IPlayerCommandAccepter;
import model.services.IPlayerDigger;
import model.services.IPlayerMover;
import model.services.MoveType;
import model.services.PlayerCommandType;
import model.services.Status;

public class HumanPlayerEngine implements IHumanPlayerEngine
{
	private IGameState state;
	private Status status;
	
	private PlayerCommandType player_command;
	
	private IPlayerMover player_mover;
	private IPlayerDigger player_digger;
	
	private IGuardMover guard_mover;
	private IGuardClimber guard_climber;
	
	public HumanPlayerEngine(IGameState state)
	{
		this.state = state;
		this.status = Status.PAUSE;
		
		this.player_command = null;
		
		this.player_mover = new PlayerMover();
		this.player_digger = new PlayerDigger();
		
		this.guard_mover = new GuardMover();
		this.guard_climber = new GuardClimber();
	}
	
	@Override
	public IGameState getState()
	{
		return state;
	}

	@Override
	public void step(long elapsed)
	{
		IEntityPool pool = state.getPool();
		
		stepPlayer(pool.getPlayer(), elapsed);
		
		for(IGuard guard : pool.getGuards())
			stepGuard(guard, elapsed);
	}
	
	private void stepPlayer(IPlayer player, long elapsed)
	{
		if(player.hasOperation())
		{
			player.getExecutedOperation().update(elapsed);
			
			if(player.getExecutedOperation().isEnded())
			{
				player.setNoOperation();
				
				player_mover.move(MoveType.NEUTRAL, player);
			}
		}
		else
		{
			if(hasCommand())
			{
				computePlayerCommand(player, player_command);
				player_command = null;
			}
			else
				computePlayerCommand(player, PlayerCommandType.NEUTRAL);
			// we consider something might happen when Neutral, at any moment
		}
	}
	
	private boolean hasCommand()
	{
		return player_command != null;
	}
	
	private void stepGuard(IGuard guard, long elapsed)
	{
		if(guard.hasOperation())
		{
			guard.getExecutedOperation().update(elapsed);

			if(guard.getExecutedOperation().isEnded())
			{
				System.out.println("hey");
				guard.setNoOperation();

				guard_mover.move(MoveType.NEUTRAL, guard);
			}
		}
		else
		{
			IEntityPool pool = state.getPool();
			IPlayer player = pool.getPlayer();
			
			IAStarDecision<IGuard> decision = new AStarDecision<>(new AStarCalculator<>(), player.getCell(), guard_mover, new RandomDecision<>(guard_mover.getAccepter()));
			
			GuardCommandType guard_command = GuardCommandType.get(decision.getCommand(guard));
			System.out.println(guard_command);
			computeGuardCommand(guard, guard_command);
		}
	}
	
	private void computePlayerCommand(IPlayer player, PlayerCommandType player_command)
	{
		IOperationsSpeeds speeds = state.getSpeeds();

		if(player_command.isMoveType())
			player_mover.move(player_command.moveType(), player);
		if(player_command.isDigType())
			player_digger.dig(player_command.digType(), player);
		
		player.setExecutedOperation(new ExecutedOperation<>(player_command, speeds.get(player_command)));

		//System.out.println("Player : " + accepter.accept(player));
	}
	
	private void computeGuardCommand(IGuard guard, GuardCommandType guard_command)
	{
		IOperationsSpeeds speeds = state.getSpeeds();
		
		if(guard_command.isMoveType())
			guard_mover.move(guard_command.moveType(), guard);
		if(guard_command.isClimbType())
			guard_climber.climb(guard_command.climbType(), guard);
		
		guard.setExecutedOperation(new ExecutedOperation<>(guard_command, speeds.get(guard_command)));
		
		//System.out.println("Guard : " + guard_accepter.accept(guard));
	}
	
	@Override
	public void addCommand(PlayerCommandType command)
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

}
