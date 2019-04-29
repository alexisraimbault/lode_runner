package model.algorithms;

import java.util.Set;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IContent;
import model.services.IHookingMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class HookingMoveAccepter<Character extends ICharacter>
	extends DeducingAccepter<Character, MoveType>
	implements IHookingMoveAccepter<Character>
{

	public HookingMoveAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, Character character)
	{
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
	}

}
