package model.services;

public interface IOperation<OperationType extends Enum<OperationType>>
{
	public OperationType getOperationType();
	public long getOperationTime();
	
	/*
	 * invariants:
	 * 	getOperationType() const
	 * 	getOperationTime() const
	 */
}
