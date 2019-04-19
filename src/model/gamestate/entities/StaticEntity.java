package model.gamestate.entities;

import model.services.IEnvironment;
import model.services.IStaticEntity;

public abstract class StaticEntity implements IStaticEntity
{
	private IEnvironment environment;
	private int x;
	private int y;
	
	public StaticEntity(IEnvironment environment, int x, int y)
	{
		this.environment = environment;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public IEnvironment getEnvironment()
	{
		return environment;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}
}
