package contract.algorithms;

import java.util.Set;

import contract.contracterr.PostconditionError;
import decorator.algorithms.NoPlentyMoveAccepterDecorator;
import model.gamestate.entities.Cell;
import model.services.ICharacter;
import model.services.INoPlentyMoveAccepter;
import model.services.MoveType;

public class NoPlentyMoveAccepterContract<Character extends ICharacter> extends NoPlentyMoveAccepterDecorator<Character>{
	CommandAccepterContract<Character,MoveType> similiHerit;
	public NoPlentyMoveAccepterContract(INoPlentyMoveAccepter<Character> d) {
		super(d);
		similiHerit = new CommandAccepterContract<Character,MoveType>(d);
	}
	
	public Set<MoveType> accept(Character cell){
		return similiHerit.accept(cell);
	}
	
	/**
	 * post : 
	 * plenty_tester.test(Cell.getNext(character, type)) => @result = false
	 * else @result = true
	 */
	
	public boolean accept(MoveType type, Character character){
		checkInvariant();
		boolean res = super.accept(type, character);
		checkInvariant();
		if(getPredicate().test(Cell.getNext(character, type))){
			if(res)
				throw new PostconditionError("NoPlentyMoveAccepter -> accept : this move should not be accepted");
		}else{
			if(!res)
				throw new PostconditionError("NoPlentyMoveAccepter -> accept : this move should be accepted");
		}
		
		return res;
	}
	
	public void checkInvariant(){
		
	}
}
