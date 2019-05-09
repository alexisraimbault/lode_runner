package model.algorithms;

import java.util.Set;

import model.gamestate.entities.Cell;
import model.services.IBlockingHoleMoveAccepter;
import model.services.ICell;
import model.services.ICharacter;
import model.services.MoveType;
import model.services.Nature;

public class BlockingHoleMoveAccepter
	<Character extends ICharacter>
		extends DeducingAccepter<Character, MoveType>
		implements IBlockingHoleMoveAccepter<Character>
{

	public BlockingHoleMoveAccepter()
	{
		super(MoveType.class);
	}
	
	@Override
	public boolean accept(MoveType type, Character character)
	{
		Nature nature = character.getNature();
		
		if(nature == Nature.HOLE)
			return false;
		
		return true;
	}

}
