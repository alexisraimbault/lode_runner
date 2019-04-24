package model.gamestate.entities;

import model.services.EntityType;
import model.services.GuardCommandType;
import model.services.ICell;
import model.services.IGuard;

public class Guard extends AbstractOperatingCharacter<GuardCommandType> implements IGuard
{
	public Guard(ICell cell)
	{
		super(cell);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.GUARD;
	}
	
}

