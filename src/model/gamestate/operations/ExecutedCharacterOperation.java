package model.gamestate.operations;

import model.services.IExecutedCharacterOperation;

public class ExecutedCharacterOperation<OperationType extends Enum<OperationType>> extends ExecutedOperation implements IExecutedCharacterOperation<OperationType>
{
	private OperationType type;
	
	public ExecutedCharacterOperation(OperationType type, long operation_time, long elapsed_time)
	{
		super(operation_time, elapsed_time);
		this.type = type;
	}
	
	public ExecutedCharacterOperation(OperationType type, long operation_time)
	{
		this(type, operation_time, 0);
	}

	@Override
	public OperationType getOperationType()
	{
		return type;
	}

}
