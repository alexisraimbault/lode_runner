package model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import model.algorithms.GuardCommandApplier;
import model.algorithms.PlayerCommandAccepter;
import model.algorithms.PlayerCommandApplier;
import model.algorithms.RandomDecision;
import model.algorithms.StupidGuardCommandApplier;
import model.gamestate.Hole;
import model.gamestate.entities.Cell;
import model.gamestate.entities.Guard;
import model.gamestate.operations.ExecutedCharacterOperation;
import model.algorithms.AStarCalculator;
import model.algorithms.StupidGuardDecision;
import model.algorithms.GuardCommandAccepter;
import model.services.EntityType;
import model.services.GuardCommandType;
import model.services.ICell;
import model.services.ICoinSummoner;
import model.services.IPlayerSummoner;
import model.services.IGameState;
import model.services.IGuard;
import model.services.IGuardCommandAccepter;
import model.services.IHole;
import model.services.IGuardCommandApplier;
import model.services.IGuardSummoner;
import model.services.IHumanPlayerEngine;
import model.services.IOperationsSpeeds;
import model.services.IPlayer;
import model.services.IPlayerCommandAccepter;
import model.services.IPlayerCommandApplier;
import model.services.IStupidGuardDecision;
import model.services.ISummonerPool;
import model.services.ITreasureSummoner;
import model.services.MoveType;
import model.services.Nature;
import model.services.PlayerCommandType;
import model.services.Status;

public class HumanPlayerEngine implements IHumanPlayerEngine
{
	private IGameState state;
	private Status status;
	
	private IPlayerCommandAccepter player_command_accepter;
	private IPlayerCommandApplier player_command_applier;
	
	private IGuardCommandAccepter guard_command_accepter;
	private IGuardCommandApplier guard_command_applier;
	
	private PlayerCommandType player_command_type;
	
	private IStupidGuardDecision guard_decision;

	// data for better complexity
	private List<IGuardSummoner> to_trap_guards;
	private List<IGuardSummoner> start_moving_guards;
	
	public HumanPlayerEngine(IGameState state)
	{
		this.state = state;
		this.status = Status.PAUSE;
		
		this.player_command_accepter = new PlayerCommandAccepter();
		this.player_command_applier = new PlayerCommandApplier();
		
		this.guard_command_accepter = new GuardCommandAccepter();
		this.guard_command_applier = new GuardCommandApplier();
		
		this.player_command_type = null;
		
		this.guard_decision = new StupidGuardDecision(
				new AStarCalculator<>(), // shortest path calculator used
				new StupidGuardCommandApplier(), // way to find a path (ignores characters, and ignores holes (can walk on them and go throw) and characters)
				new RandomDecision<IGuard, GuardCommandType>(guard_command_accepter)); // alternative decision when no path found
		
		this.to_trap_guards = new ArrayList<>();
		this.start_moving_guards = new ArrayList<>();
	}
	
	@Override
	public IGameState getState()
	{
		return state;
	}

	@Override
	public void step(long elapsed)
	{
		stepUpdateOperation(elapsed); // step all operations (and may end them)
		
		// between operation ..
		
		stepRespawn(); // player and guards respawn
		
		stepCheckCollision(); // player collides with guard = loss
		
		stepBlockTrappedGuards(); // guards ending their moves in a hole are trapped
		
		stepCollect(); // player may collect a treasure or a coin
		
		// new operation ..
		
		stepDecision(); // player and guards search a new operation to do
		
		// what's next ..
		
		stepSpotTrappedGuards(); // spot guards starting to go in a trap
		
		stepKillTrappedGuards(); // holes kill guards when it fills (and maybe the player)
		
		stepCheckLives(); // player with 0 lives = loss
		
		stepUpdateWinStatus(); // player at the top of the map + all treasures collected = win
		
		stepResetTick();
	}
	
	private void stepResetTick()
	{
		start_moving_guards.clear();
		state.getPool().clearItems();
	}
	
	private void stepBlockTrappedGuards()
	{
		ListIterator<IGuardSummoner> psto_trap_guard = to_trap_guards.listIterator();
		while(psto_trap_guard.hasNext())
		{
			IGuardSummoner sto_trap_guard = psto_trap_guard.next();
			if(!sto_trap_guard.hasInstance())
			{
				// the hole already killed the guard
				psto_trap_guard.remove();
			}
			else
			{
				IGuard to_trap_guard = sto_trap_guard.getInstance();
				// if the guard finally entered the hole
				if(!to_trap_guard.hasOperation())
				{
					long blocking_time = state.getSpeeds().get(GuardCommandType.BLOCKING);
					to_trap_guard.block(blocking_time);
					
					psto_trap_guard.remove();
				}
			}
			
		}
	}
	
	private void stepRespawn()
	{
		for(IGuardSummoner sguard : state.getPool().getGuardSummoners())
		{
			if(!sguard.hasInstance())
			{
				ICell cell = state.getGuardRespawnCell();
				cell.getContent().add(EntityType.GUARD);
				sguard.respawn(new Guard(cell));
			}
		}
		IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
		if(!splayer.hasInstance())
		{
			splayer.respawn();
		}
	}

	private void stepCollect()
	{
		IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
		
		if(splayer.hasInstance())
		{
			ISummonerPool pool = state.getPool();
			for(ITreasureSummoner streasure : pool.getTreasureSummoners())
			{
				if(splayer.canCollect(streasure))
				{
					splayer.collect(streasure);
				}
			}
			for(ICoinSummoner scoin : pool.getCoinSummoners())
			{
				if(splayer.canCollect(scoin))
				{
					splayer.collect(scoin);
				}
			}
		}
	}
	
	private void stepUpdateWinStatus()
	{
		IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
		if(splayer.hasInstance() && splayer.wins(state.getScoreToReach()))
		{
			this.status = Status.WIN;
		}
	}
	
	private boolean isEnteringHole(IGuard guard)
	{
		return guard.getExecutedOperation().getOperationType() == GuardCommandType.DOWN
				&& guard.getNature() == Nature.HOLE;
	}

	private void stepSpotTrappedGuards()
	{
		for(IGuardSummoner sguard : start_moving_guards)
			if(isEnteringHole(sguard.getInstance()))
			{
				to_trap_guards.add(sguard);
				for(IHole hole : state.getHoles())
				{
					if(hole.equals(sguard.getInstance()))
						hole.trap(sguard);
				}
			}
	}

	private void stepKillTrappedGuards()
	{
		ListIterator<IHole> phole = state.getHoles().listIterator();
		while(phole.hasNext())
		{
			IHole hole = phole.next();
			if(hole.isEnded())
			{
				hole.fill();
				
				IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
				if(splayer.hasInstance())
				{
					if(splayer.getInstance().equals(hole))
						splayer.destroy();
				}
				phole.remove();
			}
		}
	}
	
	private boolean hasWinningMove(Set<GuardCommandType> accepted)
	{
		return true; // TODO
	}

	private void stepUpdateOperation(long elapsed)
	{
		ISummonerPool pool = state.getPool();
		
		IPlayerSummoner cplayer = pool.getPlayerSummoner();
		if(cplayer.hasInstance())
		{
			IPlayer player = cplayer.getInstance();
			if(player.hasOperation())
				player.update(elapsed);
		}
		
		for(IGuard guard : pool.getGuards())
			if(guard.hasOperation())
				guard.update(elapsed);
		
		for(IHole hole : state.getHoles())
			hole.update(elapsed);
	}
	
	private boolean isFalling(IPlayer player, Set<PlayerCommandType> accepted)
	{
		Nature nature = player.getNature();
		Nature next_nature = Cell.getNext(player, MoveType.DOWN).getNature();
		
		return accepted.size() == 1
				&& accepted.iterator().next() == PlayerCommandType.DOWN
				&& (nature == Nature.EMPTY || nature == Nature.HOLE)
				&& (next_nature == Nature.EMPTY || next_nature == Nature.HOLE || next_nature == Nature.HANDRAIL);
	}
	
	private void stepDecision()
	{
		IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
		if(player_command_type != null)
		{
			if(stepDecisionPlayer(splayer, player_command_type))
				player_command_type = null;
		}
		else
			stepDecisionPlayer(splayer);
			
		
		for(IGuardSummoner sguard : state.getPool().getGuardSummoners())
			stepDecisionGuard(sguard);
	}
	
	private boolean stepDecisionPlayer(IPlayerSummoner splayer, PlayerCommandType player_command_type)
	{
		if(splayer.hasInstance())
		{
			IPlayer player = splayer.getInstance();
			Set<PlayerCommandType> accepted = player_command_accepter.accept(player);
			if(!player.hasOperation())
			{
				if(isFalling(player, accepted)) // player is falling, we force him to go down
				{
					computeBeginCommand(player, accepted.iterator().next());
					return true;
				}
				else if(player_command_type != null)
				{
					if(accepted.contains(player_command_type))
					{
						computeBeginCommand(player, player_command_type);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	private void stepDecisionPlayer(IPlayerSummoner splayer)
	{
		if(splayer.hasInstance())
		{
			IPlayer player = splayer.getInstance();
			Set<PlayerCommandType> accepted = player_command_accepter.accept(player);
			if(!player.hasOperation())
			{
				if(isFalling(player, accepted)) // player is falling, we force him to go down
				{
					computeBeginCommand(player, accepted.iterator().next());
					player_command_type = null;
				}
			}
		}
	}
	
	private void stepDecisionGuard(IGuardSummoner sguard)
	{
		if(sguard.hasInstance())
		{
			IGuard guard = sguard.getInstance();
			if(!guard.hasOperation())
			{
				IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
				if(splayer.hasInstance())
				{
					guard_decision.setTarget(splayer.getInstance());
					GuardCommandType guard_command_type = guard_decision.getCommand(guard);
					if(guard_command_type != null) // includes falling
					{
						computeBeginCommand(guard, guard_command_type);
						start_moving_guards.add(sguard);
					}
				}
			}
		}
	}
	
	private void computeBeginCommand(IPlayer player, PlayerCommandType player_command)
	{
		IOperationsSpeeds speeds = state.getSpeeds();
		
		player_command_applier.apply(player_command, player);
		
		if(player_command.isDigType())
		{
			switch(player_command.digType())
			{
			case DIGLEFT:
				state.getHoles().add(new Hole(player.getEnvironment(), player.getX() - 1, player.getY() - 1, speeds.getHoleSpeed()));
				break;
			case DIGRIGHT:
				state.getHoles().add(new Hole(player.getEnvironment(), player.getX() + 1, player.getY() - 1, speeds.getHoleSpeed()));
				break;
			default:
				break;
			
			}
		}
		
		player.setExecutedOperation(new ExecutedCharacterOperation<>(player_command, speeds.get(player_command)));
	}
	
	private void computeBeginCommand(IGuard guard, GuardCommandType guard_command)
	{
		IOperationsSpeeds speeds = state.getSpeeds();
		
		guard_command_applier.apply(guard_command, guard);
		
		if(guard_command.isClimbType())
		{
			guard.unblock();
		}
		
		guard.setExecutedOperation(new ExecutedCharacterOperation<>(guard_command, speeds.get(guard_command)));
	}
	
	private void stepCheckCollision()
	{
		ISummonerPool pool = state.getPool();
		
		IPlayerSummoner splayer = pool.getPlayerSummoner();
		
		if(splayer.hasInstance())
		{
			IPlayer player = splayer.getInstance();
			
			for(IGuard guard : pool.getGuards())
			{
				if(guard.equals(player))
				{
					this.status = Status.LOSS;
					break;
				}
			}
		}
	}
	
	private void stepCheckLives()
	{
		IPlayerSummoner splayer = state.getPool().getPlayerSummoner();
		
		if(splayer.getNbLives() == 0)
		{
			this.status = Status.LOSS;
		}
	}
	
	@Override
	public void addCommand(PlayerCommandType player_command_type)
	{
		this.player_command_type = player_command_type;
	}

	@Override
	public void start()
	{
		status = Status.PLAYING;
	}

	@Override
	public void stop()
	{
		status = Status.PAUSE;
	}

	@Override
	public Status getStatus()
	{
		return status;
	}

}
