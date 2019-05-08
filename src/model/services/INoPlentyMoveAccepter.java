package model.services;

import java.util.function.Predicate;

public interface INoPlentyMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{
	
	/**
	 * 
	 * 
	 * post : 
	 * this.plenty_tester == plenty_tester
	 */
	
	// init(Predicate<ICell> plenty_tester);
	/**
	 * post : 
	 * plenty_tester.test(Cell.getNext(character, type)) => @result = false
	 * else @result = true
	 */
	
	public boolean accept(MoveType type, Character character);
	/*{
		ICell next_cell = Cell.getNext(character, type);
		
		if(plenty_tester.test(next_cell))
			return false;
		
		return true;
	}*/

	Predicate<ICell> getPredicate();
}
