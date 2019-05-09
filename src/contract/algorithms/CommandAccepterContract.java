package contract.algorithms;

import java.util.Set;

import contract.contracterr.PostconditionError;
import decorator.algorithms.CommandAccepterDecorator;
import model.services.ICell;
import model.services.ICommandAccepter;

public class CommandAccepterContract<Cell extends ICell,CommandType extends Enum<CommandType>> extends CommandAccepterDecorator<Cell, CommandType>{

	public CommandAccepterContract(ICommandAccepter<Cell, CommandType> d) {
		super(d);
		checkInvariant();
	}
	
	
	//TODO
	public boolean accept(CommandType type, Cell cell){
		checkInvariant();
		boolean res = super.accept(type, cell);
		checkInvariant();
		return res;
	}
	
	/*
	 * post:
	 * 	forall command
	 * 		accept(command, entity) <=> accept(entity).contains(command)
	 */
	public Set<CommandType> accept(Cell cell){
		checkInvariant();
		Set<CommandType> res = super.accept(cell);
		checkInvariant();
		for(CommandType command : res)
		{
			if(!(accept(command, cell) == super.accept(cell).contains(command)))
				throw new PostconditionError("CommandAccepterContract -> accept : a command should not be accepted");
		}
		
		return res;
	}
	
	
	public void checkInvariant() {
		
	}

}
