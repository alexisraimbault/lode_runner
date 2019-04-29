package model.gamestate.operations;

import model.services.IExecutedOperation;

public class ExecutedOperation
		extends Operation
		implements IExecutedOperation
{

	private long elapsed_time;

	public ExecutedOperation(long operation_time, long elapsed_time)
	{
		super(operation_time);
		this.elapsed_time = elapsed_time;
	}
	
	public ExecutedOperation(long operation_time)
	{
		this(operation_time, 0);
	}

	@Override
	public long getElapsedTime()
	{
		return elapsed_time;
	}

	@Override
	public void update(long elapsed)
	{
		elapsed_time += elapsed;
	}
	
	@Override
	public boolean isEnded()
	{
		return getElapsedTime() >= getOperationTime();
	}

	@Override
	public double getProgress()
	{
		return ((double)getElapsedTime()) / getOperationTime();
	}

}
