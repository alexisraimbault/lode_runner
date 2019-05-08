package model.services;

import java.util.function.Predicate;

public interface IWalkingMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{

	/*private Predicate<ICell> plenty_predicate;
	
	public WalkingMoveAccepter(Predicate<ICell> plenty_predicate)
	{
		super(MoveType.class);
		this.plenty_predicate = plenty_predicate;
	}*/
	
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
