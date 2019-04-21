package model.services;

import java.util.Set;

public interface ICommandAccepter<Entity extends IEntity, CommandType>
{
	Set<CommandType> accept(Entity entity);
}
