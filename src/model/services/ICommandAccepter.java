package model.services;

import java.util.Set;

public interface ICommandAccepter
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
{
	public boolean accept(CommandType type, Entity entity);
	
	/*
	 * post:
	 * 	forall command
	 * 		accept(command, entity) <=> accept(entity).contains(command)
	 */
	public Set<CommandType> accept(Entity entity);
}
