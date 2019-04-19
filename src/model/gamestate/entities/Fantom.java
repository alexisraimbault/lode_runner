package model.gamestate.entities;

import model.gamestate.entities.Character;
import model.services.EntityType;
import model.services.IEnvironment;
import model.services.IFantom;

public class Fantom extends Character implements IFantom
{

	public Fantom(IEnvironment environment, int x, int y)
	{
		super(environment, x, y);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.FANTOM;
	}
	
}
