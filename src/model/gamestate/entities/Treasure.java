package model.gamestate.entities;

import model.services.EntityType;
import model.services.IEnvironment;
import model.services.ITreasure;

public class Treasure extends StaticEntity implements ITreasure
{
	public Treasure(IEnvironment environment, int x, int y)
	{
		super(environment, x, y);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.TREASURE;
	}

}
