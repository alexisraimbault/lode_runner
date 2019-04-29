package model.services;

public interface ICommandApplier
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
{

	public ICommandAccepter<Entity, CommandType> getAccepter();
	
	public void apply(CommandType type, Entity entity);
}
