package model.services;

import model.gamestate.entities.Cell;

public interface IHookingMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{
	
	
	/**
	 * post :
	 * character.getNature() == Nature.LADDER =>  @result = TRUE
	 * match TYPE :
	 * 		LEFT + RIGHT : 
	 * 			character.getNature() == Nature.LADDER =>  @result = TRUE
	 * 			Cell.getNext(character, MoveType.DOWN).getNature() == nature.LADDER =>  @result = TRUE
	 * 			else,  @result = FALSE
	 * 		DOWN :
	 * 			character.getNature() == Nature.HANDRAIL =>  @result = TRUE
	 * 		UP
	 * 			 @result = FALSE
	 * else,  @result = FALSE
	 * 
	 */
	public boolean accept(MoveType type, Character character);
	/*{
		Nature nature = character.getNature();
		
		if(nature == Nature.LADDER)
			return true;
		
		switch(type)
		{
		case LEFT:
		case RIGHT:
		{
			if(nature == Nature.HANDRAIL)
				return true;
			
			ICell down_cell = Cell.getNext(character, MoveType.DOWN);
			Nature down_nature = down_cell.getNature();
			
			if(down_nature == Nature.LADDER)
				return true;

			return false;
		}
		case DOWN:
			if(nature == Nature.HANDRAIL)
				return true;
		case UP:
			return false;
		default:
			break;
		}
		
		return false;
	}*/
}
