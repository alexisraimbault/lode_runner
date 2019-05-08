package contract.algorithms;

import java.util.Set;

import contract.contracterr.PostconditionError;
import decorator.algorithms.StopAtBorderMoveAccepterDecorator;
import model.gamestate.entities.Cell;
import model.services.ICharacter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.MoveType;

public class StopAtBorderMoveAccepterContract<Character extends ICharacter> extends StopAtBorderMoveAccepterDecorator<Character> {
	CommandAccepterContract<Character,MoveType> similiHerit;
	public StopAtBorderMoveAccepterContract(IStopAtBorderMoveAccepter<Character> d) {
		super(d);
		similiHerit = new CommandAccepterContract<Character,MoveType>(d);
	}
	
	public Set<MoveType> accept(Character cell){
		return similiHerit.accept(cell);
	}
	
	/*
	 * post:
	 * 		@result = Cell.hasNext(character, type)
	 */
	public boolean accept(MoveType type, Character entity){
		boolean res = accept(type, entity);
		
		if(!(res == Cell.hasNext(entity, type)))
			throw new PostconditionError("StopAtBorderMoveAccepter -> accept : the result of accept is not what it should be");
		
		return res;
	}

}
