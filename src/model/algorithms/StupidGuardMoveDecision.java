package model.algorithms;

import model.services.IAStarCalculator;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IGuardMover;
import model.services.IPlayer;
import model.services.IRandomGuardMoveDecision;
import model.services.IStupidGuardMoveDecision;
import model.services.IStupidGuardMover;
import model.services.MoveType;

public class StupidGuardMoveDecision 
	extends TestingShortestPathDecision<IGuard, MoveType> 
		implements IStupidGuardMoveDecision
{

	public StupidGuardMoveDecision(IPlayer target, IStupidGuardMover applier,
			IAStarCalculator<IGuard, MoveType> calculator, IRandomGuardMoveDecision alternative_decision)
	{
		super(target, applier, calculator, alternative_decision);
	}
	
	public StupidGuardMoveDecision(IPlayer target)
	{
		this(target, new StupidGuardMover(), new AStarCalculator<>(), new RandomGuardMoveDecision());
	}
	
	public IPlayer getTarget()
	{
		return (IPlayer)super.getTarget();
	}
	
	public IStupidGuardMover getApplier()
	{
		return (IStupidGuardMover)super.getApplier();
	}
	
	public IAStarCalculator<IGuard, MoveType> getCalculator()
	{
		return (IAStarCalculator<IGuard, MoveType>)super.getCalculator();
	}
	
	public IRandomGuardMoveDecision getAlternativeDecision()
	{
		return (IRandomGuardMoveDecision )super.getAlternativeDecision();
	}
	
	public IGuardMoveAccepter getAccepter()
	{
		return (IGuardMoveAccepter)super.getAccepter();
	}

}
