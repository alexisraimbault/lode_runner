package model.services;

public interface IDecision
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
{
	public ICommandAccepter<Entity, CommandType> getAccepter();
	
	/*
	 * pre:
	 * 	!getAccepter().accept(entity).isEmpty()
	 * 
	 * post:
	 * 	getAccepter().accept(entity).contains(getCommand(entity))
	 */
	public CommandType getCommand(Entity entity);
}
