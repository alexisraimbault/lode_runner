package model.services;

public interface IOperation
{
	public long getOperationTime();
	
	/*
	 * invariants:
	 * 	getOperationTime() > 0
	 * 	getOperationTime() const
	 */
}
