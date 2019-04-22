package model.services;

public interface IBaseEngine
{
	public IGameState getState();
	public IOperationsSpeeds getOperationsSpeeds();
	public Status getStatus();
	
	public void start();
	public void stop();
}
