package model.algorithms;

import model.gamestate.entities.Cell;
import model.services.DigType;
import model.services.ICell;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.IPlayer;
import model.services.IPlayerDigAccepter;
import model.services.MoveType;
import model.services.Nature;

public class PlayerDigAccepter extends DeducingAccepter<IPlayer, DigType> implements IPlayerDigAccepter
{
	
	public PlayerDigAccepter()
	{
		super(DigType.class);
	}

	public boolean accept(DigType type, IPlayer player)
	{
		if(player.getY() == 0)
			return false;
		
		Nature nature = player.getNature();
		
		if(nature == Nature.EMPTY || nature == Nature.HOLE)
		{
			ICell down_cell = Cell.getNext(player, MoveType.DOWN);
			Nature down_nature = down_cell.getNature();
			IContent down_content = down_cell.getContent();
			
			if(!(down_nature.isPlenty() || down_nature == Nature.LADDER || down_content.nbCharacters() > 0))
				return false;
		}
		
		switch(type)
		{
		case DIGLEFT:
		{
			if(player.getX() == 0)
				return false;
			
			ICell next_cell = Cell.getNext(player, MoveType.LEFT);
			Nature next_nature = next_cell.getNature();
			
			if(next_nature.isPlenty())
				return false;
			
			ICell todig_cell = Cell.getNext(next_cell, MoveType.DOWN);
			Nature todig_nature = todig_cell.getNature();

			if(todig_nature != Nature.PLATFORM)
				return false;
			
			return true;
		}
		case DIGRIGHT:
		{
			if(player.getX() == player.getEnvironment().getWidth() - 1)
				return false;
			
			ICell next_cell = Cell.getNext(player, MoveType.RIGHT);
			Nature next_nature = next_cell.getNature();
			
			if(next_nature.isPlenty())
				return false;
			
			ICell todig_cell = Cell.getNext(next_cell, MoveType.DOWN);
			Nature todig_nature = todig_cell.getNature();

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
