package model.services;

public interface IEngine
{
	public IGameState getState();
	public Status getStatus();

	/*
	 * pre:
	 * 	elapsed >= 0
	 */
	void step(long elapsed);
	
	/*
	 * pre:
	 * 	getStatus() == Status.PAUSE
	 */
	public void start();
	
	/*
	 * pre:
	 * 	getStatus() == Status.PLAYING
	 */
	public void stop();
}
