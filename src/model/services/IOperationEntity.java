package model.services;

public interface IOperationEntity<OperationType extends Enum<OperationType>> extends ICharacter
{
	public IExecutedCharacterOperation<OperationType> getExecutedOperation();
	public boolean hasOperation();
	
	public void setExecutedOperation(IExecutedCharacterOperation<OperationType> operation);
	public void setNoOperation();
	public void update(long elapsed);
}
