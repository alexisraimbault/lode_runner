package model.algorithms;

import model.services.ICharacter;
import model.services.ICharacterMover;
import model.services.ICharacterMoveAccepter;
import model.services.IContent;

public class CharacterMover implements ICharacterMover
{
	private ICharacterMoveAccepter policy;
	
	public CharacterMover(ICharacterMoveAccepter policy)
	{
		this.policy = policy;
	}

	@Override
	public ICharacterMoveAccepter getPolicy()
	{
		return policy;
	}

	@Override
	public void moveLeft(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		if(policy.acceptLeft(character))
		{
			IContent content = character.getEnvironment().getCellContent(x, y);
			IContent next_content = character.getEnvironment().getCellContent(x - 1, y);
			
			character.setX(x - 1);
			content.remove(character.getType());
			next_content.add(character.getType());
		}
	}

	@Override
	public void moveRight(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		if(policy.acceptRight(character))
		{
			IContent content = character.getEnvironment().getCellContent(x, y);
			IContent next_content = character.getEnvironment().getCellContent(x + 1, y);
			
			character.setX(x + 1);
			content.remove(character.getType());
			next_content.add(character.getType());
		}
	}

	@Override
	public void moveDown(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		if(policy.acceptDown(character))
		{
			IContent content = character.getEnvironment().getCellContent(x, y);
			IContent next_content = character.getEnvironment().getCellContent(x, y - 1);
			
			character.setY(y - 1);
			content.remove(character.getType());
			next_content.add(character.getType());
		}
	}

	@Override
	public void moveUp(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		if(policy.acceptUp(character))
		{
			IContent content = character.getEnvironment().getCellContent(x, y);
			IContent next_content = character.getEnvironment().getCellContent(x, y + 1);
			
			character.setY(y + 1);
			content.remove(character.getType());
			next_content.add(character.getType());
		}
	}
}
