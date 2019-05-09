package contract.entities;

import contract.contracterr.InitError;
import contract.contracterr.InvariantError;
import contract.contracterr.PreconditionError;
import decorator.entities.GuardDecorator;
import model.services.EntityType;
import model.services.IGuard;

public class GuardContract extends GuardDecorator{

	public GuardContract(IGuard d) {
		super(d);
		checkInvariant();
		checkInit();
	}

	public void checkInvariant() {
		if(!(getType() == EntityType.GUARD))
			throw new InvariantError("a guard is not a guard entity");
		if(isWaiting() && isBlocked())
			throw new InvariantError("a guard can't be waiting and blocked at the same time");
	}
	
	/*
	 * init:
	 * 	!isBlocked()
	 */
	public void checkInit(){
		if(isBlocked())
			throw new InitError("Guard shouldn't be blocked at init");
		if(isWaiting())
			throw new InitError("Guard shouldn't be waiting at init");
	}
	
	@Override
	public boolean isBlocked(){
		return isBlocked();
	}
	
	public void block(long blocking_time){
		if(isBlocked())
			throw new PreconditionError("in Guard -> block : can't block a blocked guard");
		checkInvariant();
		super.block(blocking_time);
		checkInvariant();
	}
	
	/*
	 * pre:
	 * 	isBlocked()
	 */
	public void unblock(){
		checkInvariant();
		if(!isBlocked())
			throw new PreconditionError("in Guard -> unblock : can't unblock a blocked guard that is not blocked");
		super.unblock();
		checkInvariant();
	}
	
	/*
	 * pre:
	 * 	!isBlocked()
	 * 	isWaiting()
	 * 
	 * post:
	 * 	!isBlocked()
	 * 	!isWaiting()
	 */
	public void escape(){
		if(isBlocked())
			throw new PreconditionError("in Guard -> escape : can't escape if blocked");
		if(!isWaiting())
			throw new PreconditionError("in Guard -> escape : can't escape if not waiting");
		
	}
	
}
