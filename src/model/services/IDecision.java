package model.services;

public interface IDecision
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
{
	public ICommandAccepter<Entity, CommandType> getAccepter();
	
	/*
	 * post:
	 * 	getAccepter().accept(entity).contains(getCommand(entity))
	 */
	public CommandType getCommand(Entity entity);
}
