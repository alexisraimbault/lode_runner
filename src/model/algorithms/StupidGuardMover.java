package model.algorithms;

import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IMover;
import model.services.IStupidGuardMoveAccepter;
import model.services.IStupidGuardMover;
import model.services.MoveType;

public class StupidGuardMover extends Mover<IGuard> implements IStupidGuardMover
{

	public StupidGuardMover(IStupidGuardMoveAccepter accepter)
	{
		super(accepter);
	}
	
	public StupidGuardMover()
	{
		this(new StupidGuardMoveAccepter());
	}

	@Override
	public IStupidGuardMoveAccepter getAccepter()
	{
		return (IStupidGuardMoveAccepter)super.getAccepter();
	}

}
