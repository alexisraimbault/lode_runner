package model.algorithms;

import java.util.function.Predicate;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IFallingMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class FallingMoveAccepter<Character extends ICharacter> extends DeducingAccepter<Character, MoveType> implements IFallingMoveAccepter<Character>
{
	
	private Predicate<ICell> plenty_tester;
	
	
	public FallingMoveAccepter(Predicate<ICell> plenty_tester)
	{
		super(MoveType.class);
		this.plenty_tester = plenty_tester;
	}
	
	@Override
	public Predicate<ICell> getPlentyTester(){
		return plenty_tester;
	}
	
	@Override
	public boolean accept(MoveType type, Character character)
	{
		
		switch(type)
		{
		case LEFT:
			return false;
		case RIGHT:
			return false;
		case DOWN:
		{
			ICell down_cell = Cell.getNext(character, MoveType.DOWN);
			
			if(!plenty_tester.test(down_cell))
				return true;
			
			return false;
		}
		case UP:
			return false;
		default:
			break;
		}
		
		return false;
	}
}
