package model.algorithms;

import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IRandomGuardMoveDecision;
import model.services.MoveType;

public class RandomGuardMoveDecision extends RandomDecision<IGuard, MoveType> implements IRandomGuardMoveDecision
{

	public RandomGuardMoveDecision(IGuardMoveAccepter accepter)
	{
		super(accepter);
	}
	
	public RandomGuardMoveDecision()
	{
		this(new GuardMoveAccepter());
	}
	
	public IGuardMoveAccepter getAccepter()
	{
		return (IGuardMoveAccepter)super.getAccepter();
	}

}
