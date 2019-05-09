package model.gamestate;

import model.gamestate.entities.Cell;
import model.gamestate.operations.ExecutedOperation;
import model.services.IGuardSummoner;
import model.services.IEnvironment;
import model.services.IExecutedOperation;
import model.services.IHole;

public class Hole extends Cell implements IHole
{
	private IExecutedOperation operation;
	private IGuardSummoner trapped_guard;
	
	public Hole(IEnvironment environment, int x, int y, long operation_time, long elapsed_time)
	{
		super(environment, x, y);
		this.operation = new ExecutedOperation(operation_time, elapsed_time);
		this.trapped_guard = null;
	}
	
	public Hole(IEnvironment environment, int x, int y, long operation_time)
	{
		this(environment, x, y, operation_time, 0);
	}

	@Override
	public long getElapsedTime()
	{
		return operation.getElapsedTime();
	}

	@Override
	public void update(long elapsed)
	{
		operation.update(elapsed);
	}

	@Override
	public boolean isEnded()
	{
		return operation.isEnded();
	}

	@Override
	public double getProgress()
	{
		return operation.getProgress();
	}

	@Override
	public long getOperationTime()
	{
		return operation.getOperationTime();
	}
	
	@Override
	public void trap(IGuardSummoner trapped_guard)
	{
		this.trapped_guard = trapped_guard;
	}
	
	@Override
	public boolean hasTrappedGuard()
	{
		return trapped_guard != null;
	}

	@Override
	public IGuardSummoner getTrappedGuard()
	{
		return trapped_guard;
	}
	
	@Override
	public void releaseGuard()
	{
		trapped_guard = null;
	}

	@Override
	public void fill()
	{
		getEnvironment().fill(getX(), getY());
		if(hasTrappedGuard())
			getTrappedGuard().destroy();
	}

}
