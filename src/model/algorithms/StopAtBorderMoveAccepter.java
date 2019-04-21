package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.MoveType;

public class StopAtBorderMoveAccepter implements ICharacterMoveAccepter
{
	
	public boolean acceptLeft(ICharacter character)
	{
		return character.getX() > 0;
	}
	
	public boolean acceptRight(ICharacter character)
	{
		return character.getX() < character.getEnvironment().getWidth() - 1;
	}
	
	public boolean acceptDown(ICharacter character)
	{
		return character.getY() > 0;
	}
	
	public boolean acceptUp(ICharacter character)
	{
		return character.getY() < character.getEnvironment().getHeight() - 1;
	}
	
	public boolean accept(MoveType type, ICharacter character)
	{
		switch(type)
		{
		case LEFT:
			return acceptLeft(character);
		case RIGHT:
			return acceptRight(character);
		case DOWN:
			return acceptDown(character);
		case UP:
			return acceptUp(character);
		case NEUTRAL:
			return true;
		}
		return false;
	}

	@Override
	public Set<MoveType> accept(ICharacter character)
	{
		Set<MoveType> accepted = EnumSet.noneOf(MoveType.class);
		
		for(MoveType type : MoveType.values())
		{
			if(accept(type, character))
				accepted.add(type);
		}
		return accepted;
	}
	
}
