package model.services;

public interface IExecutedOperation extends IOperation
{
	/*
	 * init:
	 * 	getElapsedTime() == 0
	 */
	
	public long getElapsedTime();
	
	/*
	 * pre:
	 * 	!isEnded()
	 */
	public void update(long elapsed);
	
	public boolean isEnded();
	
	/*
	 * pre:
	 * 	!isEnded()
	 */
	public double getProgress();
	
	/*
	 * invariants:
	 * 	getElapsedTime() >= 0
	 * 	!isEnded() <=> (getElapsedTime() < getOperationTime())
	 * 	getProgress() == (getElapsedTime() / getOperationTime())
	 */
}
