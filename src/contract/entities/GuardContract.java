package contract.entities;

import contract.contracterr.InvariantError;
import contract.contracterr.PreconditionError;
import decorator.entities.GuardDecorator;
import model.services.EntityType;
import model.services.IGuard;

public class GuardContract extends GuardDecorator{

	public GuardContract(IGuard d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void checkInvariant() {
		if(!(getType() == EntityType.GUARD))
			throw new InvariantError("a guard is not a guard entity");
	}
	/*
	 * init:
	 * 	!isBlocked()
	 */
	@Override
	public boolean isBlocked(){
		return isBlocked();
	}
	
	public void block(long blocking_time){
		if(isBlocked())
			throw new PreconditionError("in Guard -> block : can't block a blocked guard");
		((GuardDecorator) delegate).block(blocking_time);
	}
	
	/*
	 * pre:
	 * 	isBlocked()
	 */
	public void unblock(){
		if(!isBlocked())
			throw new PreconditionError("in Guard -> unblock : can't unblock a blocked guard that is not blocked");
		((GuardDecorator) delegate).unblock();
	}
	
}
