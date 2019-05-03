package model.services;

public interface ICommandApplier
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
{

	public ICommandAccepter<Entity, CommandType> getAccepter();
	
	/*
	 * pre:
	 * 	getAccepter().accept(type, entity)
	 */
	public void apply(CommandType type, Entity entity);
}
