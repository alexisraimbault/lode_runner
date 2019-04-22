package model.algorithms;

import model.services.ICharacter;
import model.services.IGuardMoveAccepter;
import model.services.IGuardMover;
import model.services.MoveType;

public class GuardMover extends CharacterMoverBase implements IGuardMover
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
	public void move(MoveType type, ICharacter character)
	{
		super.move(type, character);
	}

}
