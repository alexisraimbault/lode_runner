package model.services;

public interface IOperation
{
	public long getOperationTime();
	
	/*
	 * invariants:
	 * 	getOperationType() const
	 * 	getOperationTime() const
	 */
}
