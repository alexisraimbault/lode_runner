package contract.algorithms;

import contract.contracterr.PreconditionError;
import decorator.algorithms.CommandApplierDecorator;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IEntity;

public class CommandApplierContract<Entity extends IEntity,CommandType extends Enum<CommandType>> extends CommandApplierDecorator<Entity, CommandType> {
	public CommandApplierContract(ICommandApplier<Entity, CommandType> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public ICommandAccepter<Entity, CommandType> getAccepter(){
		return super.getAccepter();
	}
	
	/*
	 * pre:
	 * 	getAccepter().accept(type, entity)
	 */
	public void apply(CommandType type, Entity entity){
		if(!super.getAccepter().accept(type, entity))
			throw new PreconditionError("CommandApplierContract -> apply : cant apply this command, it's not accepted");
		super.apply(type, entity);
	}
	
	public void checkInvariant() {
		
	}
}