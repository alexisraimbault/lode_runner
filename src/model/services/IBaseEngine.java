package model.services;

public interface IBaseEngine
{
	public IGameState getState();
	public Status getStatus();
	
	public void start();
	public void stop();
}
