package model.algorithms;

import model.services.MoveType;
import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IMover;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;

public class MoveApplier<Character extends ICharacter> implements IMover<Character>
{
	
	private ICommandAccepter<Character, MoveType> accepter;
	
	public MoveApplier(ICommandAccepter<Character, MoveType> accepter)
	{
		this.accepter = accepter;
	}

	@Override
	public ICommandAccepter<Character, MoveType> getAccepter()
	{
		return accepter;
	}

	@Override
	public void apply(MoveType type, Character character)
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
		default:
			break;
		}
	}
	
}
