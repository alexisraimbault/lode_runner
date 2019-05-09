package contract.algorithms;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.DecisionDecorator;
import model.services.ICommandAccepter;
import model.services.IDecision;
import model.services.IEntity;

public class DecisionContract <Entity extends IEntity,CommandType extends Enum<CommandType>> extends DecisionDecorator<Entity, CommandType>{

	public DecisionContract(IDecision<Entity, CommandType> d) {
		super(d);
		checkInvariant();
	}
	
	public ICommandAccepter<Entity, CommandType> getAccepter(){
		return delegate.getAccepter();
	}
	
	public CommandType getCommand(Entity entity){
		if(getAccepter().accept(entity).isEmpty())
			throw new PreconditionError("Decision -> getCommand : no command to get, accepter is empty");
		checkInvariant();
		CommandType res = delegate.getCommand(entity);
		if(!(getAccepter().accept(entity).contains(getCommand(entity))))
			throw new PostconditionError("Decision -> getCommand : the command returned is not in the list of acceptable commands");

		checkInvariant();return res;
	}
	
	public void checkInvariant() {
		
	}

}
