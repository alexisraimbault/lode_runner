package model.algorithms;

import model.services.DigType;
import model.services.IEntity;
import model.services.IEnvironment;
import model.services.IPlayerDigAccepter;
import model.services.IPlayerDigger;

public class PlayerDigger implements IPlayerDigger
{
	
	private IPlayerDigAccepter accepter;
	
	public PlayerDigger(IPlayerDigAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	public PlayerDigger()
	{
		this(new PlayerDigAccepter());
	}
	
	@Override
	public void dig(DigType type, IEntity entity)
	{
		IEnvironment environment = entity.getEnvironment();
		int x = entity.getX();
		int y = entity.getY();
		
		switch(type)
		{
		case DIGLEFT:
			environment.dig(x - 1, y - 1);
			break;
		case DIGRIGHT:
			environment.dig(x + 1, y - 1);
			break;
		default:
			break;
		
		}
	}

}
