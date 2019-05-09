package model.services;

import java.util.List;

public interface IAStarCalculator<
	Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IShortestPathCalculator<Character, CommandType>
{
	
	IAStarNode<CommandType> getTargetNode(Character source, ICell target, ICommandApplier<Character, CommandType> applier);
	
	/**
	 * post : @result = getTargetNode().getPath()
	 */
	 List<CommandType> getPath(Character source, ICell target, ICommandApplier<Character, CommandType> applier);
}
