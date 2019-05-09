package model.services;

import java.util.function.Predicate;

import model.gamestate.entities.Cell;

public interface IFallingMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{
	

	

	/**
	 * post :
	 * 
	 * match TYPE :
	 * 		LEFT :
	 * 			 @result == FALSE
	 * 		RIGHT :
	 * 			 @result == FALSE
	 * 		DOWN :
	 * 			 @result == !plenty_tester.test(Cell.getNext(character, MoveType.DOWN))
	 * 		UP
	 * 			 @result == FALSE
	 * else,  @result == FALSE
	 * 
	 */
	
	public boolean accept(MoveType type, Character character);
	/*{
		
		switch(type)
		{
		case LEFT:
			return false;
		case RIGHT:
			return false;
		case DOWN:
		{
			ICell down_cell = Cell.getNext(character, MoveType.DOWN);
			
			if(!plenty_tester.test(down_cell))
				return true;
			
			return false;
		}
		case UP:
			return false;
		default:
			break;
		}
		
		return false;
	}*/

	Predicate<ICell> getPlentyTester();
}
