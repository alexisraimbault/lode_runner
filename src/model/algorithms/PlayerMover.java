package model.algorithms;

import model.services.ICharacter;
import model.services.IPlayerMover;
import model.services.MoveType;

public class PlayerMover extends CharacterMoverBase implements IPlayerMover
{
	private PlayerMoveAccepter accepter;
	
	public PlayerMover(PlayerMoveAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	public PlayerMover()
	{
		this(new PlayerMoveAccepter(new CharacterMoveAccepter()));
	}

	@Override
	public PlayerMoveAccepter getAccepter()
	{
		return accepter;
	}

	@Override
	public void move(MoveType type, ICharacter character)
	{
		super.move(type, character);
	}

}