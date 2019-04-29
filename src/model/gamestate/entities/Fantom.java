package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IFantom;
import model.services.MoveType;

public class Fantom extends AbstractOperatingEntity<MoveType> implements IFantom
{
	

	public Fantom(ICell cell)
	{
		super(cell, EntityType.FANTOM);
	}
	
}
