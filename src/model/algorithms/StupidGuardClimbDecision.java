package model.algorithms;

import java.util.Set;

import model.gamestate.entities.Cell;
import model.services.ClimbType;
import model.services.IAStarCalculator;
import model.services.IAStarNode;
import model.services.ICell;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;
import model.services.IGuardClimber;
import model.services.IPlayer;
import model.services.IPlayerSummoner;
import model.services.IStupidGuardClimbDecision;
import model.services.MoveType;

public class StupidGuardClimbDecision implements IStupidGuardClimbDecision
{

	public StupidGuardClimbDecision(IPlayerSummoner splayer, IAStarCalculator<IGuard, MoveType> calculator, IGuardClimber climber)
	{
		this.splayer = splayer;
		this.calculator = calculator;
		this.climber = climber;
		this.accepter = climber.getAccepter();
	}
	
	public StupidGuardClimbDecision(IPlayerSummoner splayer)
	{
		this(splayer, new AStarCalculator<>(), new GuardClimber());
	}

	private IPlayerSummoner splayer;
	private IAStarCalculator<IGuard, MoveType> calculator;
	private IGuardClimber climber;
	private IGuardClimbAccepter accepter;

	@Override
	public ClimbType getCommand(IGuard guard)
	{
		Set<ClimbType> accepted = accepter.accept(guard);
		// if the guard can climb in one and only one direction
		if(accepted.size() == 1)
		{
			// then he climbs in this direction
			return accepted.iterator().next();
		}
		else // if the guard can climb in the two directions
		{
			// if the player is alive
			if(splayer.hasInstance())
			{
				IPlayer player = splayer.getInstance();
				// then the guard choses between the two directions according to the player position

				IAStarCalculator<IGuard, MoveType> calculator = new AStarCalculator<>();
				ICell current_position = new Cell(guard);
				
				// calculate left side node
				climber.apply(ClimbType.CLIMBLEFT, guard);
				IAStarNode<MoveType> left_target_node = calculator.getTargetNode(guard, player, new StupidGuardMoveApplier());
				guard.setPosition(current_position);
				
				// calculate right side node
				climber.apply(ClimbType.CLIMBRIGHT, guard);
				IAStarNode<MoveType> right_target_node = calculator.getTargetNode(guard, player, new StupidGuardMoveApplier());
				guard.setPosition(current_position);
				
				if(left_target_node != null)
				{
					// if a path has been found on left and right side
					if(right_target_node != null)
					{
						// chose the climb command with the lowest heuristic
						if(left_target_node.getWeight() > right_target_node.getWeight())
							return ClimbType.CLIMBRIGHT;
						else
							return ClimbType.CLIMBLEFT;
						
					}
					// if a path has been found on left side
					else
						return ClimbType.CLIMBLEFT;
				}
				else
				{
					// if a path has been found on right side
					if(right_target_node != null)
						return ClimbType.CLIMBRIGHT;
					// no path found on both sides, climb left anyway
					else
						return ClimbType.CLIMBLEFT;
				}
			}
			// the player is not alive, climb left anyway
			else
			{
				return ClimbType.CLIMBLEFT;
			}
		}
	}

	@Override
	public IGuardClimbAccepter getAccepter()
	{
		return accepter;
	}

	@Override
	public IAStarCalculator<IGuard, MoveType> getCalculator()
	{
		return calculator;
	}

	@Override
	public IPlayerSummoner getPlayerSummoner()
	{
		return splayer;
	}

}
