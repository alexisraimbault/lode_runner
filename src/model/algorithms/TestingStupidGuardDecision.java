package model.algorithms;

import java.util.List;
import java.util.Set;

import model.services.GuardCommandType;
import model.services.ITestingDecision;
import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IDecision;
import model.services.ICharacter;
import model.services.IGuard;
import model.services.IGuardCommandAccepter;
import model.services.IPlayer;
import model.services.IRandomDecision;
import model.services.IRandomGuardDecision;
import model.services.IShortestPathCalculator;
import model.services.IShortestPathDecision;
import model.services.IStupidGuardCommandApplier;
import model.services.IStupidGuardDecision;
import model.services.ITestingStupidGuardDecision;
import model.services.MoveType;

public class TestingStupidGuardDecision extends TestingDecision<IGuard, GuardCommandType> implements ITestingStupidGuardDecision
{
	private ICell target;
	
	public TestingStupidGuardDecision(ICell target)
	{
		super(
				new StupidGuardDecision(target),
				new RandomGuardDecision());
		this.target = target;
	}

	@Override
	public IGuardCommandAccepter getAccepter()
	{
		return (IGuardCommandAccepter)getAlternativeDecision().getAccepter();
	}
	
	@Override
	public IStupidGuardDecision getTestingDecision()
	{
		return (IStupidGuardDecision)super.getTestingDecision();
	}
	
	@Override
	public IRandomGuardDecision getAlternativeDecision()
	{
		return (IRandomGuardDecision)super.getAlternativeDecision();
	}
	
	@Override
	public GuardCommandType getCommand(IGuard guard)
	{
		return super.getCommand(guard);
	}

}
