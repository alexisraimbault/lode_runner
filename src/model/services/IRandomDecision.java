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
	 * post:
	 * forall(type : accepted)
	 * 	probability(type) = 1\accepted.size()
	 */
	CommandType getCommand(Character character);
}
