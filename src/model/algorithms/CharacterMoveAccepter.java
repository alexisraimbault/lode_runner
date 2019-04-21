package model.algorithms;

import java.util.Set;

import model.services.CharacterMoveType;
import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.Nature;

public class CharacterMoveAccepter extends StopAtBorderMoveAccepter implements ICharacterMoveAccepter
{
	@Override
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

	@Override
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
	
	@Override
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
	
	@Override
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

	@Override
	public Set<CharacterMoveType> accept(ICharacter entity)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
