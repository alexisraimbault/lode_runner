package model;

import java.util.List;

import model.algorithms.GuardClimber;
import model.algorithms.GuardMover;
import model.algorithms.PlayerDigger;
import model.algorithms.PlayerMover;
import model.algorithms.ShortestPathsCalculator;
import model.services.ICharacterMover;
import model.services.IEntityPool;
import model.services.IEnvironment;
import model.services.IGameState;
import model.services.IGuard;
import model.services.IGuardClimber;
import model.services.IHumanPlayerEngine;
import model.services.IPlayer;
import model.services.IPlayerDigger;
import model.services.IShortestPathsCalculator;
import model.services.PlayerCommand;
import model.services.Status;

public class HumanPlayerEngine implements IHumanPlayerEngine
{
	private IGameState state;
	private Status status;
	
	private PlayerCommand command;
	
	private ICharacterMover player_mover;
	private IPlayerDigger player_digger;
	
	private ICharacterMover guard_mover;
	private IGuardClimber guard_climber;
	
	private IShortestPathsCalculator paths_calculator;
	
	public HumanPlayerEngine(IGameState state)
	{
		this.state = state;
		this.status = Status.PAUSE;
		
		this.command = PlayerCommand.NEUTRAL;
		
		this.player_mover = new PlayerMover();
		this.player_digger = new PlayerDigger();
		
		this.guard_mover = new GuardMover();
		this.guard_climber = new GuardClimber();
		
		this.paths_calculator = new ShortestPathsCalculator();
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
		
		switch(command)
		{
		case DIGLEFT:
			player_digger.digLeft(player);
			break;
		case DIGRIGHT:
			player_digger.digRight(player);
			break;
		case DOWN:
			player_mover.moveDown(player);
			break;
		case LEFT:
			player_mover.moveLeft(player);
			break;
		case NEUTRAL:
			break;
		case RIGHT:
			player_mover.moveRight(player);
			break;
		case UP:
			player_mover.moveUp(player);
			break;
		default:
			break;
		}
		command = PlayerCommand.NEUTRAL;
	}
	
	@Override
	public void stepGuards()
	{
		IEnvironment environment = state.getEnvironment();
		IEntityPool pool = state.getPool();
		List<IGuard> guards = pool.getGuards();
		
		for(IGuard guard : guards)
		{
			int[][] paths = paths_calculator.getPaths(guard, guard_mover.getPolicy());
			
			// TODO
		}
	}
	
	@Override
	public void setCommand(PlayerCommand command)
	{
		this.command = command;
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
