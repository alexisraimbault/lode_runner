package model.algorithms;

import model.services.GuardCommandType;
import model.services.IGuard;
import model.services.IGuardCommandAccepter;
import model.services.IRandomGuardDecision;

public class RandomGuardDecision extends RandomDecision<IGuard, GuardCommandType> implements IRandomGuardDecision
{

	public RandomGuardDecision(IGuardCommandAccepter accepter)
	{
		super(accepter);
	}
	
	public RandomGuardDecision()
	{
		this(new GuardCommandAccepter());
	}
	
	public IGuardCommandAccepter getAccepter()
	{
		return (IGuardCommandAccepter)super.getAccepter();
	}

}
