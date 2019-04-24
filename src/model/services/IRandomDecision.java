package model.services;

public interface IRandomDecision
	<Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IDecision<Character, CommandType>
{
	
}
