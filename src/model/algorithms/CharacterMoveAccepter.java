package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.MoveType;
import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.Nature;

public class CharacterMoveAccepter extends StopAtBorderMoveAccepter implements ICharacterMoveAccepter
{
	@Override
	public Set<MoveType> accept(ICharacter character)
	{
		Set<MoveType> accepted = EnumSet.noneOf(MoveType.class);
		
		for(MoveType type : MoveType.values())
		{
			if(type != MoveType.NEUTRAL && accept(type, character))
				accepted.add(type);
		}
		if(!(accepted.size() == 1 && accepted.contains(MoveType.DOWN)))
			accepted.add(MoveType.NEUTRAL);
		return accepted;
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
		default:
			break;
		}
		assert false;
		return false;
	}
	
	public boolean acceptLeft(ICharacter character)
	{
		if(!super.acceptLeft(character))
			return false;
		
		IEnvironment environment = character.getEnvironment();
		int x = character.getX();
		int y = character.getY();
		
		Nature nature = environment.getCellNature(x, y);
		Nature next_nature = environment.getCellNature(x - 1, y);
		IContent next_content = environment.getCellContent(x - 1, y);
		
		if(next_nature.isPlenty())
			return false;
		
		if(next_content.nbCharacters() > 0)
			return false;
		
		if(y > 0)
		{
			Nature down_nature = environment.getCellNature(x, y - 1);
			IContent down_content = environment.getCellContent(x, y - 1);
			
			if(down_nature == Nature.LADDER || down_nature.isPlenty())
				return true;
			
			if(down_content.nbCharacters() > 0)
				return true;
		}
		
		if(nature == Nature.LADDER || nature == Nature.HANDRAIL)
			return true;
		
		return false;
	}
	
	public boolean acceptRight(ICharacter character)
	{
		if(!super.acceptRight(character))
			return false;
		
		IEnvironment environment = character.getEnvironment();
		int x = character.getX();
		int y = character.getY();
		
		Nature nature = environment.getCellNature(x, y);
		Nature next_nature = environment.getCellNature(x + 1, y);
		IContent next_content = environment.getCellContent(x + 1, y);
		
		if(next_nature.isPlenty())
			return false;
		
		if(next_content.nbCharacters() > 0)
			return false;
		
		if(y > 0)
		{
			Nature down_nature = environment.getCellNature(x, y - 1);
			IContent down_content = environment.getCellContent(x, y - 1);
			
			if(down_nature == Nature.LADDER || down_nature.isPlenty())
				return true;
			
			if(down_content.nbCharacters() > 0)
				return true;
		}
		
		if(nature == Nature.LADDER || nature == Nature.HANDRAIL)
			return true;
		
		return false;
	}
	
	public boolean acceptDown(ICharacter character)
	{
		if(!super.acceptDown(character))
			return false;
		
		IEnvironment environment = character.getEnvironment();
		int x = character.getX();
		int y = character.getY();
		
		Nature next_nature = environment.getCellNature(x, y - 1);
		IContent next_content = environment.getCellContent(x, y - 1);
		
		if(next_nature.isPlenty())
			return false;
		
		if(next_content.nbCharacters() > 0)
			return false;
		
		return false;
	}
	
	public boolean acceptUp(ICharacter character)
	{
		if(!super.acceptUp(character))
			return false;
		
		IEnvironment environment = character.getEnvironment();
		int x = character.getX();
		int y = character.getY();
		
		Nature nature = environment.getCellNature(x, y);
		Nature next_nature = environment.getCellNature(x, y + 1);
		IContent next_content = environment.getCellContent(x, y + 1);
		
		if(next_nature.isPlenty())
			return false;
		
		if(next_content.nbCharacters() > 0)
			return false;
		
		if(nature == Nature.LADDER)
			return true;
		
		return false;
	}

}
