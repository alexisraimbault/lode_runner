package model.algorithms;

import model.services.ICharacter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.MoveType;

public class StopAtBorderMoveAccepter extends DeducingAccepter<ICharacter, MoveType> implements IStopAtBorderMoveAccepter
{
	
	public StopAtBorderMoveAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, ICharacter character)
	{
		switch(type)
		{
		case LEFT:
			return character.getX() > 0;
		case RIGHT:
			return character.getX() < character.getEnvironment().getWidth() - 1;
		case DOWN:
			return character.getY() > 0;
		case UP:
			return character.getY() < character.getEnvironment().getHeight() - 1;
		case NEUTRAL:
			return true;
		}
		return false;
	}
	
}
