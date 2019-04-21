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
	public void setPosition(int x, int y)
	{
		environment.getCellContent(this.x, this.y).remove(getType());
		this.x = x;
		this.y = y;
		environment.getCellContent(this.x, this.y).add(getType());
	}
	
	@Override
	public void setX(int x)
	{
		setPosition(x, getY());
	}
	
	@Override
	public void setY(int y)
	{
		setPosition(getX(), y);
	}
}
