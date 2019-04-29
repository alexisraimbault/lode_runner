package model.algorithms;

import java.util.function.Predicate;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IContent;
import model.services.IWalkingMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class WalkingMoveAccepter<Character extends ICharacter>
extends DeducingAccepter<Character, MoveType>
implements IWalkingMoveAccepter<Character>
{
	
	private Predicate<ICell> plenty_predicate;
	
	public WalkingMoveAccepter(Predicate<ICell> plenty_predicate)
	{
		super(MoveType.class);
		this.plenty_predicate = plenty_predicate;
	}
	
	@Override
	public boolean accept(MoveType type, Character character)
	{
		
		switch(type)
		{
		case LEFT:
		case RIGHT:
		{
			ICell down_cell = Cell.getNext(character, MoveType.DOWN);
			
			if(plenty_predicate.test(down_cell))
				return true;
			
			return false;
		}
		case DOWN:
			return false;
		case UP:
			return false;
		default:
			break;
		}
		
		return false;
	}

}