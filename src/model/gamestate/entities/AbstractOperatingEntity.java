package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IExecutedCharacterOperation;
import model.services.IOperationEntity;

public abstract class AbstractOperatingEntity<OperationType extends Enum<OperationType>> extends AbstractEntity implements IOperationEntity<OperationType>
{
	private IExecutedCharacterOperation<OperationType> operation;
	
	public AbstractOperatingEntity(ICell cell, EntityType type)
	{
		super(cell, type);
		this.operation = null;
	}
	
	@Override
	public IExecutedCharacterOperation<OperationType> getExecutedOperation()
	{
		return operation;
	}

	@Override
	public boolean hasOperation()
	{
		return operation != null;
	}

	@Override
	public void setExecutedOperation(IExecutedCharacterOperation<OperationType> operation)
	{
		this.operation = operation;
	}
	
	@Override
	public void setNoOperation()
	{
		this.operation = null;
	}

	@Override
	public void update(long elapsed)
	{
		operation.update(elapsed);
		if(operation.isEnded())
			setNoOperation();
	}

}
