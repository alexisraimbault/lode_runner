package decorator.algorithms;

import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IEntity;

public class CommandApplierDecorator<Entity extends IEntity,CommandType extends Enum<CommandType>> implements ICommandApplier<Entity, CommandType> {
	ICommandApplier<Entity, CommandType> delegate;
	public CommandApplierDecorator(ICommandApplier<Entity, CommandType> d) {
		delegate = d;
	}
	public ICommandAccepter<Entity, CommandType> getAccepter() {
		return delegate.getAccepter();
	}
	public void apply(CommandType type, Entity entity) {
		delegate.apply(type, entity);
	}
}
