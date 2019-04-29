package model.gamestate.entities;

import model.services.IEntity;
import model.services.ISummoner;

public class AbstractSummoner<Entity extends IEntity> implements ISummoner<Entity>
{
	private Entity instance;
	
	public AbstractSummoner(Entity instance)
	{
		this.instance = instance;
	}
	
	@Override
	public boolean hasInstance()
	{
		return instance != null;
	}

	@Override
	public Entity getInstance()
	{
		return instance;
	}

	@Override
	public void respawn(Entity instance)
	{
		this.instance = instance;
	}

	@Override
	public void destroy()
	{
		instance.getContent().remove(instance.getType());
		instance = null;
	}

}
