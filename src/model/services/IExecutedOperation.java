package model.services;

public interface IExecutedOperation extends IOperation
{
	public long getElapsedTime();
	public void update(long elapsed);
	public boolean isEnded();
	public double getProgress();
}
