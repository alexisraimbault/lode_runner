package model.gamestate;

import model.services.IEntityPool;
import model.services.IEnvironment;
import model.services.IGameState;
import model.services.IOperationsSpeeds;

public class GameState implements IGameState
{
	private IEnvironment environment;
	private IOperationsSpeeds speeds;
	private IEntityPool pool;
	
	public GameState(IEnvironment environment, IOperationsSpeeds speeds)
	{
		this.environment = environment;
		this.speeds = speeds;
		this.pool = new EntityPool(environment);
	}
	
	@Override
	public IEnvironment getEnvironment()
	{
		return environment;
	}

	@Override
	public IEntityPool getPool()
	{
		return pool;
	}

	@Override
	public IOperationsSpeeds getSpeeds()
	{
		return speeds;
	}

}
