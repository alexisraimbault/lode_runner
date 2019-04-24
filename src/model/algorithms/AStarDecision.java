package model.algorithms;

import java.util.List;

import model.services.IAStarDecision;
import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.IMover;
import model.services.IRandomDecision;
import model.services.IShortestPathCalculator;
import model.services.MoveType;

public class AStarDecision<Character extends ICharacter> implements IAStarDecision<Character>
{
	private IMover<Character> mover;
	private ICell target;
	private IShortestPathCalculator<Character> calculator;
	private IRandomDecision<Character, MoveType> random_decision;
	
	public AStarDecision(IShortestPathCalculator<Character> calculator, ICell target, IMover<Character> mover, IRandomDecision<Character, MoveType> random_decision)
	{
		this.calculator = calculator;
		this.target = target;
		this.mover = mover;
		this.random_decision = random_decision;
	}

	@Override
	public ICommandAccepter<Character, MoveType> getAccepter()
	{
		return mover.getAccepter();
	}

	@Override
	public IShortestPathCalculator<Character> getCalculator()
	{
		return calculator;
	}

	@Override
	public ICell getTarget()
	{
		return target;
	}
	
	@Override
	public MoveType getCommand(Character entity)
	{
		List<MoveType> path = calculator.getPath(entity, target, mover);
		if(path == null || path.isEmpty())
			return random_decision.getCommand(entity);
		else
			return path.get(0);
	}

}
