package model.services;

public interface IWalkingOnCharacterMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{
	/*
	 * post:
	 * 	accept(type, character) =
	 * 		match type
	 * 		LEFT	->	character.getCell().getContent().nbCharacters() == 0
	 * 		RIGHT	->	character.getCell().getContent().nbCharacters() == 0
	 * 		DOWN	->	character.getCell().getContent().nbCharacters() == 0
	 * 		UP		->	character.getCell().getContent().nbCharacters() == 0
	 */
}
