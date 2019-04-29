package model.algorithms;

import java.util.List;
import java.util.Set;

import model.services.GuardCommandType;
import model.services.IAStarDecision;
import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IDecision;
import model.services.ICharacter;
import model.services.IGuard;
import model.services.IPlayer;
import model.services.IRandomDecision;
import model.services.IShortestPathCalculator;
import model.services.IStupidGuardDecision;
import model.services.MoveType;

public class StupidGuardDecision implements IStupidGuardDecision
{
	private IShortestPathCalculator<IGuard, GuardCommandType> calculator;
	private ICommandApplier<IGuard, GuardCommandType> pseudo_mover;
	private IDecision<IGuard, GuardCommandType> alternative_decision;

	private ICommandAccepter<IGuard, GuardCommandType> pseudo_accepter;
	private ICommandAccepter<IGuard, GuardCommandType> good_accepter;
	private ICell target;
	
	public StupidGuardDecision(
			IShortestPathCalculator<IGuard, GuardCommandType> calculator,
			ICommandApplier<IGuard, GuardCommandType> pseudo_mover,
			IRandomDecision<IGuard, GuardCommandType> alternative_decision)
	{
		this.calculator = calculator;
		this.pseudo_mover = pseudo_mover;
		this.alternative_decision = alternative_decision;
		
		this.pseudo_accepter = pseudo_mover.getAccepter();
		this.good_accepter = alternative_decision.getAccepter();
		this.target = null;
	}

	@Override
	public ICommandAccepter<IGuard, GuardCommandType> getAccepter()
	{
		return good_accepter;
	}

	@Override
	public IShortestPathCalculator<IGuard, GuardCommandType> getCalculator()
	{
		return calculator;
	}

	@Override
	public ICell getTarget()
	{
		return target;
	}
	
	@Override
	public GuardCommandType getCommand(IGuard guard)
	{
		Set<GuardCommandType> accepted = good_accepter.accept(guard);
		
		if(accepted.isEmpty())
			return null;
		
		List<GuardCommandType> path = calculator.getPath(guard, target, pseudo_mover);
		
		if(path == null)
			return alternative_decision.getCommand(guard);
		else if(path.isEmpty())
			return null;
		else
		{
			GuardCommandType guard_command_type = path.get(0);
			if(accepted.contains(guard_command_type))
				return guard_command_type;
			else
				return alternative_decision.getCommand(guard);
		}
	}

	@Override
	public boolean hasTarget()
	{
		return target != null;
	}

	@Override
	public void setTarget(ICell target)
	{
		this.target = target;
	}

}
