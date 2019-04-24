package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IPlayer;
import model.services.PlayerCommandType;

public class Player extends AbstractOperatingCharacter<PlayerCommandType> implements IPlayer
{
	public Player(ICell cell)
	{
		super(cell);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.PLAYER;
	}
	
}
