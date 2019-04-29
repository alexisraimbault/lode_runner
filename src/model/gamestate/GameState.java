package model.gamestate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.IEnvironment;
import model.services.IGameState;
import model.services.IHole;
import model.services.IOperationsSpeeds;
import model.services.ISummonerPool;
import model.services.Nature;

public class GameState implements IGameState
{
	private IEnvironment environment;
	private ISummonerPool pool;
	private List<IHole> holes;
	private IOperationsSpeeds speeds;
	private int score_to_reach;
	
	public GameState(IEnvironment environment, IOperationsSpeeds speeds, int nb_lives)
	{
		this.environment = environment;
		this.speeds = speeds;
		this.pool = new SummonerPool(environment, nb_lives);
		this.holes = new ArrayList<>();
		this.score_to_reach = pool.getTreasures().size();
	}
	
	@Override
	public IEnvironment getEnvironment()
	{
		return environment;
	}

	@Override
	public ISummonerPool getPool()
	{
		return pool;
	}

	@Override
	public IOperationsSpeeds getSpeeds()
	{
		return speeds;
	}

	@Override
	public int getScoreToReach()
	{
		return score_to_reach;
	}

	@Override
	public List<IHole> getHoles()
	{
		return holes;
	}

	@Override
	public ICell getGuardRespawnCell()
	{
		Random random = new Random();
		
		Predicate<ICell> tester = 
					cell ->
					(cell.getNature() == Nature.EMPTY
					|| cell.getNature() == Nature.HANDRAIL
					|| cell.getNature() == Nature.LADDER)
					&& cell.getContent().nbCharacters() == 0;
		
		int x = Math.abs(random.nextInt() % getEnvironment().getWidth());
		int y = getEnvironment().getHeight() - 1;
		ICell chosen_cell = new Cell(getEnvironment(), x, y);
		
		while(!tester.test(chosen_cell))
		{
			x = Math.abs(random.nextInt() % getEnvironment().getWidth());
			chosen_cell = new Cell(getEnvironment(), x, y);
		}
		
		return chosen_cell;
	}

}
