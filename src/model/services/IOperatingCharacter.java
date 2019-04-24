package model.services;

public interface IOperatingCharacter<OperationType extends Enum<OperationType>> extends ICharacter
{
	public IExecutedOperation<OperationType> getExecutedOperation();
	public boolean hasOperation();
	
	public void setExecutedOperation(IExecutedOperation<OperationType> operation);
	public void setNoOperation();
}
