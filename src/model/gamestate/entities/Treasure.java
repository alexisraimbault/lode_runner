package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.ITreasure;

public class Treasure extends Item implements ITreasure
{
	public Treasure(ICell cell)
	{
		super(cell);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.TREASURE;
	}

}
