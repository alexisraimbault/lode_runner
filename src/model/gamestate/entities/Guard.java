package model.gamestate.entities;

import model.services.EntityType;
import model.services.IEnvironment;
import model.services.IGuard;

public class Guard extends Character implements IGuard
{

	private int time_in_hole;
	
	public Guard(IEnvironment environment, int x, int y, int time_in_hole)
	{
		super(environment, x, y);
		this.time_in_hole = time_in_hole;
	}
	
	public Guard(IEnvironment environment, int x, int y)
	{
		this(environment, x, y, 0);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.GUARD;
	}

	@Override
	public int timeInHole()
	{
		return time_in_hole;
	}
	
}

