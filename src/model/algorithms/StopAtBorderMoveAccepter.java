package model.algorithms;

import model.gamestate.entities.Cell;
import model.services.ICharacter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.MoveType;

public class StopAtBorderMoveAccepter<Character extends ICharacter> extends DeducingAccepter<Character, MoveType> implements IStopAtBorderMoveAccepter<Character>
{
	
	public StopAtBorderMoveAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, Character character)
	{
		return Cell.hasNext(character, type);
	}
	
}
