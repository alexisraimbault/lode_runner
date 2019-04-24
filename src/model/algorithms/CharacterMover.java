package model.algorithms;

import model.services.MoveType;
import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;

public abstract class CharacterMover
{
	
	public ICell nextCell(MoveType type, ICharacter character)
	{
		return Cell.getNext(character.getCell(), type);
	}
	
	public void moveCharacter(MoveType type, ICharacter character)
	{
		int x = character.getX();
		int y = character.getY();
		switch(type)
		{
		case LEFT:
			character.setX(x - 1);
			break;
		case RIGHT:
			character.setX(x + 1);
			break;
		case DOWN:
			character.setY(y - 1);
			break;
		case UP:
			character.setY(y + 1);
			break;
		case NEUTRAL:
			break;
		default:
			break;
		
		}
	}
	
}
