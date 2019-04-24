package model.algorithms;

import model.services.ICell;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IGuardMover;
import model.services.MoveType;

public class GuardMover extends CharacterMover implements IGuardMover
{
	private IGuardMoveAccepter accepter;
	
	public GuardMover(IGuardMoveAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	public GuardMover()
	{
		this(new GuardMoveAccepter(new CharacterMoveAccepter()));
	}

	@Override
	public IGuardMoveAccepter getAccepter()
	{
		return accepter;
	}

	@Override
	public void move(MoveType type, IGuard guard)
	{
		super.moveCharacter(type, guard);
	}

	@Override
	public ICell next(MoveType type, IGuard guard)
	{
		return super.nextCell(type, guard);
	}

}
