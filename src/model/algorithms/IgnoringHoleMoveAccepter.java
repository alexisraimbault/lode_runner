package model.algorithms;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IHookingMoveAccepter;
import model.services.IIgnoringHoleMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class IgnoringHoleMoveAccepter<Character extends ICharacter>
extends DeducingAccepter<Character, MoveType>
implements IIgnoringHoleMoveAccepter<Character>
{
	
	public IgnoringHoleMoveAccepter()
	{
		super(MoveType.class);
	}
	
	@Override
	public boolean accept(MoveType type, Character character)
	{
		Nature nature = character.getNature();
		
		switch(type)
		{
		case LEFT:
		case RIGHT:
		{
			ICell down_cell = Cell.getNext(character, MoveType.DOWN);
			Nature down_nature = down_cell.getNature();
			
			if(down_nature == Nature.HOLE)
				return true;
	
			return false;
		}
		case DOWN:
			return false;
		case UP:
			return false;
		default:
			break;
		}
		
		return false;
	}
}