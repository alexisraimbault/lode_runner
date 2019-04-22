package model.algorithms;

import java.util.Set;

import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IGuardMoveAccepter;
import model.services.MoveType;

public class GuardMoveAccepter implements IGuardMoveAccepter
{
	private ICharacterMoveAccepter accepter;
	
	public GuardMoveAccepter(ICharacterMoveAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	@Override
	public Set<MoveType> accept(ICharacter entity)
	{
		return accepter.accept(entity);
	}
}
