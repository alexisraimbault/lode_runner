package contract.algorithms;

import java.util.Set;

import contract.contracterr.PostconditionError;
import decorator.algorithms.WalkingMoveAccepterDecorator;
import model.gamestate.entities.Cell;
import model.services.ICharacter;
import model.services.IWalkingMoveAccepter;
import model.services.MoveType;

public class WalkingMoveAccepterContract<Character extends ICharacter> extends WalkingMoveAccepterDecorator<Character> {
	CommandAccepterContract<Character,MoveType> similiHerit;
	public WalkingMoveAccepterContract(IWalkingMoveAccepter<Character> d) {
		super(d);
		similiHerit = new CommandAccepterContract<Character,MoveType>(d);
	}
	
	public Set<MoveType> accept(Character cell){
		return similiHerit.accept(cell);
	}
	
	/**
	 * CASE : type
	 * 		LEFT + RIGHT :
	 * 			@result = plenty_predicate.test(Cell.getNext(character, MoveType.DOWN))
	 * 		DOWN : 
	 * 			@result = false
	 * 		UP:
	 * 			@result = false
	 * 
	 */
	public boolean accept(MoveType type, Character character){
		checkInvariant();
		boolean res = super.accept(type, character);
		checkInvariant();
		switch(type)
		{
		case LEFT:
		case RIGHT:
			if(!(res == getPredicate().test(Cell.getNext(character, MoveType.DOWN))))
				throw new PostconditionError("WalkingMoveAccepter -> accept : RIGHT - LEFT, wrong accept statement");
			break;
		case DOWN:
			if(res )
				throw new PostconditionError("WalkingMoveAccepter -> accept : DOWN -> cant accept a DOWN move");
			break;
		case UP:
			if(res)
				throw new PostconditionError("WalkingMoveAccepter -> accept : cant accept a UP move");
			break;
		default:
			break;
		}
		return res;
	}
	
	public void checkInvariant() {
		
	}

}
