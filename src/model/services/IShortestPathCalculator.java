package model.services;

import java.util.List;

public interface IShortestPathCalculator<Character extends ICharacter, CommandType extends Enum<CommandType>>
{
	public List<CommandType> getPath(Character entity, ICell target, ICommandApplier<Character, CommandType> accepter);
}
