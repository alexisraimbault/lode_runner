package model.algorithms;

import model.services.ICharacterMoveAccepter;
import model.services.IPlayer;
import model.services.IPlayerMoveAccepter;
import model.services.MoveType;

public class PlayerMoveAccepter extends DeducingAccepter<IPlayer, MoveType> implements IPlayerMoveAccepter
{
	
	private ICharacterMoveAccepter accepter;
	
	public PlayerMoveAccepter(ICharacterMoveAccepter accepter)
	{
		super(MoveType.class);
		this.accepter = accepter;
	}
	
	@Override
	public boolean accept(MoveType type, IPlayer player)
	{
		return accepter.accept(type, player);
	}
}
