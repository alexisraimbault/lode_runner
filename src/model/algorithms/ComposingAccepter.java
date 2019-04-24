package model.algorithms;

import java.util.List;

import model.services.ICommandAccepter;
import model.services.IEntity;

public abstract class ComposingAccepter
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
		extends DeducingAccepter<Entity, CommandType>
		implements ICommandAccepter<Entity, CommandType>
{
	private List<ICommandAccepter<Entity, CommandType>> accepters;
	
	public ComposingAccepter(List<ICommandAccepter<Entity, CommandType>> accepters, Class<CommandType> class_object)
	{
		super(class_object);
		this.accepters = accepters;
	}
	

	@Override
	public boolean accept(CommandType type, Entity entity)
	{
		for(ICommandAccepter<Entity, CommandType> accepter : accepters)
		{
			if(!accepter.accept(type, entity))
				return false;
		}
		return true;
	}

}
