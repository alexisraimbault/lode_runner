package model.algorithms;

import model.services.MoveType;
import model.services.ICharacter;
import model.services.ICharacterMover;
import model.services.ICharacterMoveAccepter;
import model.services.IContent;

public class CharacterMover implements ICharacterMover
{
	private ICharacterMoveAccepter accepter;
	
	public CharacterMover(ICharacterMoveAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	@Override
	public ICharacterMoveAccepter getAccepter()
	{
		return accepter;
	}
	
	public void moveLeft(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		IContent content = character.getEnvironment().getCellContent(x, y);
		IContent next_content = character.getEnvironment().getCellContent(x - 1, y);
			
		character.setX(x - 1);
		content.remove(character.getType());
		next_content.add(character.getType());
	}
	
	public void moveRight(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		IContent content = character.getEnvironment().getCellContent(x, y);
		IContent next_content = character.getEnvironment().getCellContent(x + 1, y);
			
		character.setX(x + 1);
		content.remove(character.getType());
		next_content.add(character.getType());
	}
	
	public void moveDown(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		IContent content = character.getEnvironment().getCellContent(x, y);
		IContent next_content = character.getEnvironment().getCellContent(x, y - 1);
			
		character.setY(y - 1);
		content.remove(character.getType());
		next_content.add(character.getType());
	}
	
	public void moveUp(ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		IContent content = character.getEnvironment().getCellContent(x, y);
		IContent next_content = character.getEnvironment().getCellContent(x, y + 1);
			
		character.setY(y + 1);
		content.remove(character.getType());
		next_content.add(character.getType());
	}
	
	@Override
	public void move(MoveType type, ICharacter character)
	{
		switch(type)
		{
		case LEFT:
			moveLeft(character);
			break;
		case RIGHT:
			moveRight(character);
			break;
		case DOWN:
			moveDown(character);
			break;
		case UP:
			moveUp(character);
			break;
		case NEUTRAL:
			break;
		default:
			break;
		
		}
	}
}
