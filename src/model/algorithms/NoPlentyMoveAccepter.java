package model.algorithms;

import java.util.function.Predicate;

import model.gamestate.entities.Cell;
import model.services.ICell;
import model.services.ICharacter;
import model.services.INoPlentyMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class NoPlentyMoveAccepter<Character extends ICharacter> extends DeducingAccepter<Character, MoveType> implements INoPlentyMoveAccepter<Character>
{
	
	private Predicate<ICell> plenty_tester;
	
	public NoPlentyMoveAccepter(Predicate<ICell> plenty_tester)
	{
		super(MoveType.class);
		this.plenty_tester = plenty_tester;
	}
	
	@Override
	public Predicate<ICell> getPredicate()
	{
		return plenty_tester;
	}

	@Override
	public boolean accept(MoveType type, Character character)
	{
		ICell next_cell = Cell.getNext(character, type);
		
		if(plenty_tester.test(next_cell))
			return false;
		
		return true;
	}

}
