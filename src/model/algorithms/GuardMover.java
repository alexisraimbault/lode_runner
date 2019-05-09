package model.algorithms;

import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IGuardMover;
import model.services.MoveType;

public class GuardMover extends Mover<IGuard> implements IGuardMover
{

	public GuardMover(IGuardMoveAccepter accepter)
	{
		super(accepter);
	}
	
	public GuardMover()
	{
		this(new GuardMoveAccepter());
	}
	
	public IGuardMoveAccepter getAccepter()
	{
		return (IGuardMoveAccepter)super.getAccepter();
	}

}
