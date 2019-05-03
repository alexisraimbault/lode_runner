package model.algorithms;

import java.util.List;

import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IShortestPathCalculator;
import model.services.IShortestPathDecision;

public class ShortestPathDecision<
	Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		implements IShortestPathDecision<Character, CommandType>
{
	
	private ICommandApplier<Character, CommandType> applier;
	private IShortestPathCalculator<Character, CommandType> calculator;
	private ICell target;
	
	public ShortestPathDecision(ICommandApplier<Character, CommandType> applier, IShortestPathCalculator<Character, CommandType> calculator, ICell target)
	{
		this.applier = applier;
		this.calculator = calculator;
		this.target = target;
	}
	
	@Override
	public ICommandApplier<Character, CommandType> getApplier()
	{
		return applier;
	}
	
	@Override
	public ICommandAccepter<Character, CommandType> getAccepter()
	{
		return applier.getAccepter();
	}

	@Override
	public CommandType getCommand(Character entity)
	{
		List<CommandType> path = calculator.getPath(entity, target, applier);
		if(!(path == null || path.isEmpty()))
			return path.get(0);
		else
			return null;
	}

	@Override
	public IShortestPathCalculator<Character, CommandType> getCalculator()
	{
		return calculator;
	}

}
