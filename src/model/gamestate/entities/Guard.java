package model.gamestate.entities;

import model.services.EntityType;
import model.services.IEnvironment;
import model.services.IGuard;

public class Guard extends Character implements IGuard
{

	public Guard(IEnvironment environment, int x, int y)
	{
		super(environment, x, y);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.GUARD;
	}
	
}

