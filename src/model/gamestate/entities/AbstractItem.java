package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IItem;

public class AbstractItem extends AbstractEntity implements IItem
{

	public AbstractItem(ICell cell, EntityType type)
	{
		super(cell, type);
	}

}
