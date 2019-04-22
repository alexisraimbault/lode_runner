package model.services;

public interface IDecision<Entity extends IEntity, CommandType, CommandAccepter extends ICommandAccepter<Entity, CommandType>>
{
	public CommandAccepter getAccepter();
	public CommandType getCommand(Entity entity);
}
