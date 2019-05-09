package model.services;

public interface IAStarCalculator<
	Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IShortestPathCalculator<Character, CommandType>
{

	IAStarNode<CommandType> getTargetNode(Character source, ICell target, ICommandApplier<Character, CommandType> applier);
	/*
	 * getPath returns A* path using IAStarNode service
	 */
}
