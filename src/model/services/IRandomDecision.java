package model.services;

public interface IRandomDecision
	<Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IDecision<Character, CommandType>
{
	/*
	 * set:
	 * 	accepted := getAccepter.accept(character)
	 * 
	 * pre:
	 * 	!accepted.isEmpty()
	 * 
	 * post:
	 * 	accepted.contains(@result)
	 */
	// CommandType getCommand(Character character)
}
