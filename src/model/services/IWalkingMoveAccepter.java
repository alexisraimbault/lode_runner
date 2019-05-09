package model.services;

import java.util.function.Predicate;

public interface IWalkingMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{

	
	
	/**
	 * CASE : type
	 * 		LEFT + RIGHT :
	 * 			@result = plenty_predicate.test(Cell.getNext(character, MoveType.DOWN))
	 * 		DOWN : 
	 * 			@result = false
	 * 		UP:
	 * 			@result = false
	 * 
	 */
	public boolean accept(MoveType type, Character character);

	Predicate<ICell> getPredicate();
		
}
