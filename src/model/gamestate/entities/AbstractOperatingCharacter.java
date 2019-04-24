package model.gamestate.entities;

import model.services.ICell;
import model.services.IExecutedOperation;
import model.services.IOperatingCharacter;

public abstract class AbstractOperatingCharacter<OperationType extends Enum<OperationType>> extends AbstractCharacter implements IOperatingCharacter<OperationType>
{
	private IExecutedOperation<OperationType> operation;
	
	public AbstractOperatingCharacter(ICell cell)
	{
		super(cell);
		this.operation = null;
	}
	
	@Override
	public IExecutedOperation<OperationType> getExecutedOperation()
	{
		return operation;
	}

	@Override
	public boolean hasOperation()
	{
		return operation != null;
	}

	@Override
	public void setExecutedOperation(IExecutedOperation<OperationType> operation)
	{
		this.operation = operation;
	}
	
	@Override
	public void setNoOperation()
	{
		this.operation = null;
	}

}
