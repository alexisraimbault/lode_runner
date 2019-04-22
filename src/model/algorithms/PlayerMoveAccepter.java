package model.algorithms;

import java.util.Set;

import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IPlayerMoveAccepter;
import model.services.MoveType;

public class PlayerMoveAccepter implements IPlayerMoveAccepter
{
	
	private ICharacterMoveAccepter accepter;
	
	public PlayerMoveAccepter(ICharacterMoveAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	@Override
	public Set<MoveType> accept(ICharacter entity)
	{
		return accepter.accept(entity);
	}

}
