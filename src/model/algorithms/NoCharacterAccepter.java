package model.algorithms;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.INoCharacterAccepter;
import model.services.MoveType;

public class NoCharacterAccepter extends DeducingAccepter<ICharacter, MoveType> implements INoCharacterAccepter
{

	public NoCharacterAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, ICharacter character)
	{
		ICell next = Cell.getNext(character.getCell(), type);
		
		if(next.getContent().nbCharacters() > 0)
			return false;
		
		return true;
	}
}
