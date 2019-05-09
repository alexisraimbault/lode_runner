package model.services;

import java.util.function.Predicate;

public interface INoPlentyMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{
	
	/**
	 * post : 
	 * getPredicate().test(Cell.getNext(character, type)) => @result = false
	 * else @result = true
	 */
	
	public boolean accept(MoveType type, Character character);

	Predicate<ICell> getPredicate();
}
