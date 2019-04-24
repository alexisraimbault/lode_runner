package model;

import model.services.IExecutedOperation;

public class ExecutedOperation
	<CommandType extends Enum<CommandType>>
		extends Operation<CommandType>
		implements IExecutedOperation<CommandType>
{

	private long elapsed_time;

	public ExecutedOperation(CommandType type, long operation_time, long elapsed_time)
	{
		super(type, operation_time);
		this.elapsed_time = elapsed_time;
	}
	
	public ExecutedOperation(CommandType type, long operation_time)
	{
		this(type, operation_time, 0);
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

}
