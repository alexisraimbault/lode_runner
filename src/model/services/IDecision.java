package model.services;

public interface IDecision<Entity extends IEntity, CommandType>
{
	public ICommandAccepter<Entity, CommandType> getAccepter();
	public CommandType getCommand(Entity entity);
}
