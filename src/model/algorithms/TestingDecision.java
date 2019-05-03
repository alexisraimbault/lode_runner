package model.algorithms;

import java.util.Set;

import model.services.ICommandAccepter;
import model.services.IDecision;
import model.services.IEntity;
import model.services.ITestingDecision;

public class TestingDecision
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
		implements ITestingDecision<Entity, CommandType>
{
	private IDecision<Entity, CommandType> testing_decision;
	private IDecision<Entity, CommandType> alternative_decision;
	
	public TestingDecision(
			IDecision<Entity, CommandType> testing_decision,
			IDecision<Entity, CommandType> alternative_decision)
	{
		this.testing_decision = testing_decision;
		this.alternative_decision = alternative_decision;
	}
	
	@Override
	public ICommandAccepter<Entity, CommandType> getAccepter()
	{
		return getAlternativeDecision().getAccepter();
	}

	@Override
	public CommandType getCommand(Entity entity)
	{
		Set<CommandType> accepted = getAccepter().accept(entity);
		CommandType test_type = testing_decision.getCommand(entity);
		if(accepted.contains(test_type))
			return test_type;
		else
			return alternative_decision.getCommand(entity);
	}

	@Override
	public IDecision<Entity, CommandType> getTestingDecision()
	{
		return testing_decision;
	}

	@Override
	public IDecision<Entity, CommandType> getAlternativeDecision()
	{
		return alternative_decision;
	}

}
