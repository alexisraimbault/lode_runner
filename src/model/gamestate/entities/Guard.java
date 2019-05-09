package model.gamestate.entities;

import model.gamestate.operations.ExecutedCharacterOperation;
import model.services.EntityType;
import model.services.GuardCommandType;
import model.services.ICell;
import model.services.IGuard;

public class Guard extends AbstractOperatingEntity<GuardCommandType> implements IGuard
{

	private boolean is_blocked;
	private boolean is_waiting;
	
	public Guard(ICell cell)
	{
		super(cell, EntityType.GUARD);
		this.is_blocked = false;
		this.is_waiting = false;
	}

	@Override
	public boolean isBlocked()
	{
		return is_blocked;
	}

	@Override
	public void block(long blocking_time)
	{
		is_blocked = true;
		setExecutedOperation(new ExecutedCharacterOperation<>(GuardCommandType.BLOCKING, blocking_time));
	}

	@Override
	public void unblock()
	{
		is_blocked = false;
		is_waiting = true;
	}

	@Override
	public boolean isWaiting()
	{
		return is_waiting;
	}

	@Override
	public void escape()
	{
		is_waiting = false;
	}
	
}

