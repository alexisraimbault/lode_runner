package model.algorithms;

import model.services.ICharacter;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.IPlayerMovingPolicy;
import model.services.Nature;

public class PlayerCommandAccepter extends CharacterMoveAccepter implements IPlayerMovingPolicy
{
	@Override
	public boolean acceptLeft(ICharacter character)
	{
		if(!super.acceptLeft(character))
			return false;
		
		IEnvironment environment = character.getEnvironment();
		int x = character.getX();
		int y = character.getY();
		
		Nature nature = environment.getCellNature(x - 1, y);
		IContent content = environment.getCellContent(x - 1, y - 1);
		
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
		
		Nature nature = environment.getCellNature(x + 1, y);
		IContent content = environment.getCellContent(x + 1, y);
		
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
		
		Nature nature = environment.getCellNature(x, y - 1);
		IContent content = environment.getCellContent(x, y - 1);
		
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
		
		Nature nature = environment.getCellNature(x, y + 1);
		IContent content = environment.getCellContent(x, y + 1);
		
		return false;
	}
}
