package model.gamestate.entities;

import model.services.EntityType;
import model.services.IEnvironment;
import model.services.IPlayer;

public class Player extends Character implements IPlayer
{
	public Player(IEnvironment environment, int x, int y)
	{
		super(environment, x, y);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.PLAYER;
	}
	
}
