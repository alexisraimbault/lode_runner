package contract.algorithms;

import contract.contracterr.PreconditionError;
import decorator.algorithms.CommandApplierDecorator;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IEntity;

public class CommandApplierContract<Entity extends IEntity,CommandType extends Enum<CommandType>> extends CommandApplierDecorator<Entity, CommandType> {
	public CommandApplierContract(ICommandApplier<Entity, CommandType> d) {
		super(d);
		checkInvariant();
	}
	
	public ICommandAccepter<Entity, CommandType> getAccepter(){
		checkInvariant();
		ICommandAccepter<Entity, CommandType> res =  super.getAccepter();
		checkInvariant();
		return res;
	}
	
	/*
	 * pre:
	 * 	getAccepter().accept(type, entity)
	 */
	public void apply(CommandType type, Entity entity){
		if(!super.getAccepter().accept(type, entity))
			throw new PreconditionError("CommandApplierContract -> apply : cant apply this command, it's not accepted");
		checkInvariant();
		super.apply(type, entity);
		checkInvariant();
	}
	
	public void checkInvariant() {
		
	}
}