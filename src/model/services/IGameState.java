package model.services;

public interface IGameState
{
	public void setEnvironment(IEnvironment environment);
	public IEnvironment getEnvironment();
	public IEntityPool getPool();
	public IOperationsSpeeds getSpeeds();
}
