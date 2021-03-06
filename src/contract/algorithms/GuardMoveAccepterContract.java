package contract.algorithms;

import contract.contracterr.PostconditionError;
import decorator.algorithms.GuardMoveAccepterDecorator;
import decorator.algorithms.PlayerMoveAccepterDecorator;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IPlayer;
import model.services.IPlayerMoveAccepter;
import model.services.MoveType;

public class GuardMoveAccepterContract extends GuardMoveAccepterDecorator{

	public GuardMoveAccepterContract(IGuardMoveAccepter d) {
		super(d);
		checkInvariant();
	}
	
	/**
	 * post : 
	 * !stop_at_border.accept(type, entity) -> @result = false, break
	 * !no_plenty.accept(type, entity) -> @result = false, break
	 * hooking.accept(type, entity) -> @result = true, break
	 * walking.accept(type, entity) -> @result = true, break
	 * falling.accept(type, entity) -> @result = true, break
	 * @result = false
	 */
	
	public boolean accept(MoveType type, IGuard entity){
		checkInvariant();
		boolean res = accept(type, entity);
		checkInvariant();
		if(!getStop_at_border().accept(type, entity) && res)
			throw new PostconditionError("PlayerMoveAccepter -> accept : the move should not be accepted considering border collision");
		
		if(!getNo_plenty().accept(type, entity) && res)
			throw new PostconditionError("PlayerMoveAccepter -> accept :  the move should not be accepted considering plenty terrain collision");
		
		if(getNo_plenty().accept(type, entity) && getStop_at_border().accept(type, entity)){
			boolean moveAccepted = false;
			if(getHooking().accept(type, entity)){
				if(!res)
					throw new PostconditionError("PlayerMoveAccepter -> accept :  the move should be accepted bc of a true statement returned by the hooking accepter");
				else
					moveAccepted = true;
			}
			
			if(getWalking().accept(type, entity) && !res){
				if(!res)
					throw new PostconditionError("PlayerMoveAccepter -> accept :  the move should be accepted bc of a true statement returned by the walking accepter");
				else
					moveAccepted = true;
			}
			
			if(getFalling().accept(type, entity) && !res){
				if(!res)
					throw new PostconditionError("PlayerMoveAccepter -> accept :  the move should be accepted bc of a true statement returned by the falling accepter");
					
				else
					moveAccepted = true;
			}
			
			if(!moveAccepted && res)
				throw new PostconditionError("PlayerMoveAccepter -> accept :  the move should not be accepted, no accepter accepted it");
		}
		
		return res;
	}
	
	public void checkInvariant(){
		
	}

}
