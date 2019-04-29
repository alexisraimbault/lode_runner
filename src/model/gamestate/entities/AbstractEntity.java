package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IContent;
import model.services.IEntity;
import model.services.IEnvironment;
import model.services.Nature;

public class AbstractEntity implements IEntity
{
	private IEnvironment environment;
	private int x;
	private int y;
	private EntityType type;
	
	public AbstractEntity(ICell cell, EntityType type)
	{
		this.environment = cell.getEnvironment();
		this.x = cell.getX();
		this.y = cell.getY();
		this.type = type;
	}
	
	@Override
	public void setPosition(ICell cell)
	{
		getContent().remove(getType());
		this.environment = cell.getEnvironment();
		this.x = cell.getX();
		this.y = cell.getY();
		getContent().add(getType());
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
		getContent().remove(getType());
		this.x = x;
		this.y = y;
		getContent().add(getType());
	}

	@Override
	public void setX(int x)
	{
		getContent().remove(getType());
		this.x = x;
		getContent().add(getType());
	}

	@Override
	public void setY(int y)
	{
		setPosition(getX(), y);
	}

	@Override
	public Nature getNature()
	{
		return environment.getCellNature(x, y);
	}

	@Override
	public IContent getContent()
	{
		return environment.getCellContent(x, y);
	}

	@Override
	public boolean equals(ICell other)
	{
		if(getX() == other.getX() && getY() == other.getY() && getEnvironment() == other.getEnvironment())
			return true;
		else
			return false;
	}

	@Override
	public EntityType getType()
	{
		return type;
	}
	
}
