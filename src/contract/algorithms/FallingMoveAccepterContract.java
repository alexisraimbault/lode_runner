package contract.algorithms;

import java.util.Set;

import contract.contracterr.PostconditionError;
import decorator.algorithms.FallingMoveAccepterDecorator;
import model.gamestate.entities.Cell;
import model.services.ICharacter;
import model.services.IFallingMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class FallingMoveAccepterContract<Character extends ICharacter> extends FallingMoveAccepterDecorator<Character>{
	CommandAccepterContract<Character,MoveType> similiHerit;
	
	public FallingMoveAccepterContract(IFallingMoveAccepter<Character> d) {
		super(d);
		similiHerit = new CommandAccepterContract<Character,MoveType>(d);
	}
	
	public Set<MoveType> accept(Character cell){
		return similiHerit.accept(cell);
	}

	/**
	 * post :
	 * 
	 * match TYPE :
	 * 		LEFT :
	 * 			 @result = FALSE
	 * 		RIGHT :
	 * 			 @result = FALSE
	 * 		DOWN :
	 * 			 @result = !plenty_tester.test(Cell.getNext(character, MoveType.DOWN))
	 * 		UP
	 * 			 @result = FALSE
	 * else,  @result = FALSE
	 * 
	 */
	
	public boolean accept(MoveType type, Character character){
		checkInvariant();
		boolean res = super.accept(type, character);
		checkInvariant();
		switch(type)
		{
		case LEFT:
			if(res)
				throw new PostconditionError("FallingMoveAccepter -> accept : cant accept a LEFT move");
			break;
		case RIGHT:
			if(res)
				throw new PostconditionError("FallingMoveAccepter -> accept : cant accept a RIGHT move");
			break;
		case DOWN:
			if(!(res == !delegate.getPlentyTester().test(Cell.getNext(character, MoveType.DOWN))))
				throw new PostconditionError("FallingMoveAccepter -> accept : DOWN -> wrong accepter answer");
			break;
		case UP:
			if(res)
				throw new PostconditionError("FallingMoveAccepter -> accept : cant accept a UP move");
			break;
		default:
			break;
		}
		return res;
	}
	
	public void checkInvariant() {
		
	}
}
