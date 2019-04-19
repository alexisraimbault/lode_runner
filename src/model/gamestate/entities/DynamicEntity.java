package model.gamestate.entities;

import model.services.IDynamicEntity;
import model.services.IEnvironment;

public abstract class DynamicEntity implements IDynamicEntity
{
	private IEnvironment environment;
	private int x;
	private int y;
	
	public DynamicEntity(IEnvironment environment, int x, int y)
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
	
	@Override
	public void setX(int x)
	{
		this.x = x;
	}

	@Override
	public void setY(int y)
	{
		this.y = y;
	}
}
