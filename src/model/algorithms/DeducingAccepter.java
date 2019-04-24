package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.ICommandAccepter;
import model.services.IEntity;

public abstract class DeducingAccepter
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>> implements ICommandAccepter<Entity, CommandType>
{
	private Class<CommandType> class_object;
	
	public DeducingAccepter(Class<CommandType> class_object)
	{
		this.class_object = class_object;
	}
	
	@Override
	public Set<CommandType> accept(Entity entity)
	{
		Set<CommandType> accepted = EnumSet.noneOf(class_object);
		for(CommandType type : class_object.getEnumConstants())
		{
			if(accept(type, entity))
				accepted.add(type);
		}
		return accepted;
	}
	
	public Class<CommandType> getEnumClass()
	{
		return class_object;
	}
}
