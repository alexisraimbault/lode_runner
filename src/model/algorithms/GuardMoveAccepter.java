package model.algorithms;

import model.services.ICharacterMoveAccepter;
import model.services.IEntity;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.MoveType;

public class GuardMoveAccepter extends DeducingAccepter<IGuard, MoveType> implements IGuardMoveAccepter
{
	private ICharacterMoveAccepter accepter;
	
	public GuardMoveAccepter(ICharacterMoveAccepter accepter)
	{
		super(MoveType.class);
		this.accepter = accepter;
	}

	@Override
	public boolean accept(MoveType type, IGuard guard)
	{
		return accepter.accept(type, guard);
	}
	
}
