package model.algorithms;

import java.util.List;
import java.util.Set;

import model.services.ICell;
import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IDecision;
import model.services.IShortestPathCalculator;
import model.services.ITestingShortestPathDecision;

public class TestingShortestPathDecision
	<Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		implements ITestingShortestPathDecision<Character, CommandType>
{
	private ICell target;
	private ICommandApplier<Character, CommandType> applier;
	private IShortestPathCalculator<Character, CommandType> calculator;
	private IDecision<Character, CommandType> alternative_decision;
	
	public TestingShortestPathDecision(
			ICell target,
			ICommandApplier<Character, CommandType> applier,
			IShortestPathCalculator<Character, CommandType> calculator,
			IDecision<Character, CommandType> alternative_decision)
	{
		this.target = target;
		this.applier = applier;
		this.calculator = calculator;
		this.alternative_decision = alternative_decision;
	}
	
	@Override
	public ICommandAccepter<Character, CommandType> getAccepter()
	{
		return getAlternativeDecision().getAccepter();
	}

	@Override
	public CommandType getCommand(Character character)
	{
		Set<CommandType> accepted = getAccepter().accept(character);
		List<CommandType> path = getCalculator().getPath(character, getTarget(), getApplier());
		if(!(path == null || path.isEmpty()))
		{
			CommandType tested_command = path.get(0);
			if(accepted.contains(tested_command))
				return tested_command;
			else
				return alternative_decision.getCommand(character);
		}
		else
			return alternative_decision.getCommand(character);
	}

	@Override
	public IDecision<Character, CommandType> getAlternativeDecision()
	{
		return alternative_decision;
	}

	@Override
	public ICell getTarget()
	{
		return target;
	}

	@Override
	public ICommandApplier<Character, CommandType> getApplier()
	{
		return applier;
	}

	@Override
	public IShortestPathCalculator<Character, CommandType> getCalculator()
	{
		return calculator;
	}

}
