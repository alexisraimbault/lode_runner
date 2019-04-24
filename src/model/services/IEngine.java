package model.services;

public interface IEngine
{
	public IGameState getState();
	public Status getStatus();

	void step(long elapsed);
	public void start();
	public void stop();
}
