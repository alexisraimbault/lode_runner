package model.services;

public interface INoCharacterAccepter extends ICommandAccepter<ICharacter, MoveType>
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
