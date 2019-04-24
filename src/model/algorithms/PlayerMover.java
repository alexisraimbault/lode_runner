package model.algorithms;

import model.services.ICell;
import model.services.IPlayer;
import model.services.IPlayerMover;
import model.services.MoveType;

public class PlayerMover extends CharacterMover implements IPlayerMover
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
	public void move(MoveType type, IPlayer player)
	{
		super.moveCharacter(type, player);
	}

	@Override
	public ICell next(MoveType type, IPlayer player)
	{
		return super.nextCell(type, player);
	}

}