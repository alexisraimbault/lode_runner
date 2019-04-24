package model.services;

public interface IGameState
{
	public IEnvironment getEnvironment();
	public IEntityPool getPool();
	public IOperationsSpeeds getSpeeds();
}
