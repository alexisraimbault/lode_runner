package model.algorithms;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IFreeNatureAccepter;
import model.services.MoveType;

public class FreeNatureAccepter extends DeducingAccepter<ICharacter, MoveType> implements IFreeNatureAccepter
{

	public FreeNatureAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, ICharacter character)
	{
		ICell next = Cell.getNext(character.getCell(), type);
		
		if(next.getNature().isPlenty())
			return false;
		
		return true;
	}

}
