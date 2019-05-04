package decorator.algorithms;

import model.services.ICommandAccepter;
import model.services.IDecision;
import model.services.IEntity;

public class DecisionDecorator<Entity extends IEntity,CommandType extends Enum<CommandType>> implements IDecision<Entity, CommandType> {
	protected IDecision<Entity, CommandType> delegate;
	
	public DecisionDecorator(IDecision<Entity, CommandType> d) {
		delegate = d;
	}

	public ICommandAccepter<Entity, CommandType> getAccepter() {
		return delegate.getAccepter();
	}

	public CommandType getCommand(Entity entity) {
		return delegate.getCommand(entity);
	}
}
