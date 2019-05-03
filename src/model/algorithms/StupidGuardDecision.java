package model.algorithms;

import model.services.GuardCommandType;
import model.services.IAStarCalculator;
import model.services.ICell;
import model.services.IGuard;
import model.services.IStupidGuardCommandAccepter;
import model.services.IStupidGuardCommandApplier;
import model.services.IStupidGuardDecision;

public class StupidGuardDecision 
	extends ShortestPathDecision<IGuard, GuardCommandType> 
		implements IStupidGuardDecision
{

	public StupidGuardDecision(IStupidGuardCommandApplier applier,
			IAStarCalculator<IGuard, GuardCommandType> calculator,
			ICell target)
	{
		super(applier, calculator, target);
	}
	
	public StupidGuardDecision(ICell target)
	{
		this(new StupidGuardCommandApplier(), new AStarCalculator<>(), target);
	}

	@Override
	public IStupidGuardCommandApplier getApplier()
	{
		return (IStupidGuardCommandApplier)super.getApplier();
	}

	@Override
	public IAStarCalculator<IGuard, GuardCommandType> getCalculator()
	{
		return (IAStarCalculator<IGuard, GuardCommandType>)super.getCalculator();
	}

	@Override
	public IStupidGuardCommandAccepter getAccepter()
	{
		return getApplier().getAccepter();
	}

}
