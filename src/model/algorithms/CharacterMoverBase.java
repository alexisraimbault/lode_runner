package model.algorithms;

import model.services.MoveType;
import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;

public class CharacterMoverBase
{

	public void move(MoveType type, ICharacter character)
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
