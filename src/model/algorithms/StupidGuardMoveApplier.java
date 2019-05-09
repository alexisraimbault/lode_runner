package model.algorithms;

import model.services.IGuard;
import model.services.IStupidGuardMoveAccepter;
import model.services.IStupidGuardMover;

public class StupidGuardMoveApplier extends MoveApplier<IGuard> implements IStupidGuardMover
{

	public StupidGuardMoveApplier(IStupidGuardMoveAccepter accepter)
	{
		super(accepter);
	}
	
	public StupidGuardMoveApplier()
	{
		this(new StupidGuardMoveAccepter());
	}
	
	public IStupidGuardMoveAccepter getAccepter()
	{
		return (IStupidGuardMoveAccepter)super.getAccepter();
	}
	
}
