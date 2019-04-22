package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.DigType;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.IPlayer;
import model.services.IPlayerDigAccepter;
import model.services.Nature;

public class PlayerDigAccepter implements IPlayerDigAccepter
{
	@Override
	public Set<DigType> accept(IPlayer player)
	{
		Set<DigType> accepted = EnumSet.noneOf(DigType.class);
		
		for(DigType type : DigType.values())
		{
			if(accept(type, player))
				accepted.add(type);
		}
		return accepted;
	}
	
	public boolean accept(DigType type, IPlayer player)
	{
		IEnvironment environment = player.getEnvironment();
		int x = player.getX();
		int y = player.getY();
		
		if(y == 0)
			return false;

		Nature down_nature = environment.getCellNature(x, y - 1);
		IContent down_content = environment.getCellContent(x, y - 1);
		
		if(!(down_nature.isPlenty() || down_content.nbCharacters() > 0))
			return false;
		
		switch(type)
		{
		case DIGLEFT:
		{
			if(x == 0)
				return false;
			
			Nature next_nature = environment.getCellNature(x - 1, y);
			
			if(next_nature.isPlenty())
				return false;
			
			Nature todig_nature = environment.getCellNature(x - 1, y - 1);

			if(todig_nature != Nature.PLATFORM)
				return false;
			
			return true;
		}
		case DIGRIGHT:
		{
			if(x == environment.getWidth() - 1)
				return false;
			
			Nature next_nature = environment.getCellNature(x + 1, y);
			
			if(next_nature.isPlenty())
				return false;
			
			Nature todig_nature = environment.getCellNature(x + 1, y - 1);

			if(todig_nature != Nature.PLATFORM)
				return false;
			
			return true;
			
		}
		default:
			break;
		}
		return false;
	}

}
