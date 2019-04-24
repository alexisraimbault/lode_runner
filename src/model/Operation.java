package model;

import model.services.IOperation;

public class Operation<CommandType extends Enum<CommandType>> implements IOperation<CommandType>
{
	
	private CommandType type;
	private long operation_time;
	
	public Operation(CommandType type, long operation_time)
	{
		this.type = type;
		this.operation_time = operation_time;
	}
	
	@Override
	public CommandType getOperationType()
	{
		return type;
	}

	@Override
	public long getOperationTime()
	{
		return operation_time;
	}
}
