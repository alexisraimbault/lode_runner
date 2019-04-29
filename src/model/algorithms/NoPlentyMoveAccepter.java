package model.algorithms;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.INoPlentyMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class NoPlentyMoveAccepter<Character extends ICharacter> extends DeducingAccepter<Character, MoveType> implements INoPlentyMoveAccepter<Character>
{

	public NoPlentyMoveAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, Character character)
	{
		ICell next_cell = Cell.getNext(character, type);
		Nature next_nature = next_cell.getNature();
		
		if(next_nature == Nature.PLATFORM || next_nature == Nature.METAL)
			return false;
		
		return true;
	}

}
