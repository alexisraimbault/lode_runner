package model.algorithms;

import model.services.DigType;
import model.services.IEnvironment;
import model.services.IPlayer;
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
	public void dig(DigType type, IPlayer player)
	{
		IEnvironment environment = player.getEnvironment();
		int x = player.getX();
		int y = player.getY();
		
		switch(type)
		{
		case DIGLEFT:
			environment.dig(x - 1, y - 1);
		case DIGRIGHT:
			environment.dig(x + 1, y - 1);
		default:
			break;
		
		}
	}

}
