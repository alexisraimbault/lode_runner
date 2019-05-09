package contract.algorithms;

import java.util.Set;

import contract.contracterr.PostconditionError;
import decorator.algorithms.HookingMoveAccepterDecorator;
import model.gamestate.entities.Cell;
import model.services.ICharacter;
import model.services.IHookingMoveAccepter;
import model.services.MoveType;
import model.services.Nature;

public class HookingMoveAccepterContract<Character extends ICharacter> extends HookingMoveAccepterDecorator<Character>{
	CommandAccepterContract<Character,MoveType> similiHerit;
	public HookingMoveAccepterContract(IHookingMoveAccepter<Character> d) {
		super(d);
		similiHerit = new CommandAccepterContract<Character,MoveType>(d);
	}
	
	public Set<MoveType> accept(Character cell){
		return similiHerit.accept(cell);
	}
	
	/**
	 * post :
	 * character.getNature() == Nature.LADDER =>  @result = TRUE
	 * match TYPE :
	 * 		LEFT + RIGHT : 
	 * 			character.getNature() == Nature.LADDER =>  @result = TRUE
	 * 			Cell.getNext(character, MoveType.DOWN).getNature() == nature.LADDER =>  @result = TRUE
	 * 			else,  @result = FALSE
	 * 		DOWN :
	 * 			character.getNature() == Nature.HANDRAIL =>  @result = TRUE
	 * 		UP
	 * 			 @result = FALSE
	 * else,  @result = FALSE
	 * 
	 */
	public boolean accept(MoveType type, Character character){
		checkInvariant();
		boolean res = super.accept(type, character);
		checkInvariant();
		boolean accepted = false;
		if(character.getNature() == Nature.LADDER){
			if(!res)
				throw new PostconditionError("HookingMoveAccepter -> accept : the character is on a ladder so the hooking move should be accepted");
			else
				accepted = true;
		}
		switch(type)
		{
		case LEFT:
		case RIGHT:
		{
			if(character.getNature() == Nature.HANDRAIL ){
				if(! res)
					throw new PostconditionError("HookingMoveAccepter -> accept : the character is on a handrail so hooking LEFT-RIGHT moves should be accepted");
				else
					accepted = true;
			}
			if(Cell.getNext(character, MoveType.DOWN).getNature() == Nature.LADDER){
				if(!res)
					throw new PostconditionError("HookingMoveAccepter -> accept : the character is on top of a ladder so hooking move should be accepted");
				else
					accepted = true;
			}
			break;
		}
		case DOWN:
			if(character.getNature() == Nature.HANDRAIL){
				if(!res)
					throw new PostconditionError("HookingMoveAccepter -> accept : the character is on a handrail so hooking DOWN move should be accepted");
				else
					accepted = true;
			}
			break;
		case UP:
			if(res)
				throw new PostconditionError("HookingMoveAccepter -> accept : move UP should not be accepted");
			break;
		default:
			break;
		}
		if(!accepted && res)
			throw new PostconditionError("HookingMoveAccepter -> accept : this move should not be accepted");
		return res;
	}
	
	public void checkInvariant() {
		
	}
}
