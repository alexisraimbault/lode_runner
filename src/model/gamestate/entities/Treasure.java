package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.ITreasure;

public class Treasure extends AbstractEntity implements ITreasure
{


	public Treasure(ICell cell)
	{
		super(cell, EntityType.TREASURE);
	}

}
