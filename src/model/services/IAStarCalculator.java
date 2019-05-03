package model.services;

public interface IAStarCalculator<
	Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IShortestPathCalculator<Character, CommandType>
{
	/*
	 * getPath returns A* path using IAStarNode service
	 */
}
