package model.gamestate;

import model.services.IEntityPool;
import model.services.IEnvironment;
import model.services.IGameState;

public class GameState implements IGameState
{
	private IEnvironment environment;
	private IEntityPool pool;
	
	public GameState(IEnvironment environment)
	{
		this.environment = environment;
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

}
