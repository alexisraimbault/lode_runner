package model.gamestate.entities;

import model.gamestate.entities.DynamicEntity;
import model.services.ICharacter;
import model.services.IEnvironment;

public abstract class Character extends DynamicEntity implements ICharacter
{
	public Character(IEnvironment environment, int x, int y)
	{
		super(environment, x, y);
	}
}
