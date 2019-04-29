package model.gamestate.operations;

import model.services.IOperation;

public class Operation implements IOperation
{
	
	private long operation_time;
	
	public Operation(long operation_time)
	{
		this.operation_time = operation_time;
	}

	@Override
	public long getOperationTime()
	{
		return operation_time;
	}
}
