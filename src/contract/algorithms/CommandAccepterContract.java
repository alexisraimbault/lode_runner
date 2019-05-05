package contract.algorithms;

import java.util.Set;

import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.CommandAccepterDecorator;
import model.services.ICell;
import model.services.ICommandAccepter;
import model.services.MoveType;

public class CommandAccepterContract<Cell extends ICell,CommandType extends Enum<CommandType>> extends CommandAccepterDecorator<Cell, CommandType>{

	public CommandAccepterContract(ICommandAccepter<Cell, CommandType> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	
	//TODO
	public boolean accept(CommandType type, Cell cell){
		return super.accept(type, cell);
	}
	
	/*
	 * post:
	 * 	forall command
	 * 		accept(command, entity) <=> accept(entity).contains(command)
	 */
	public Set<CommandType> accept(Cell cell){
		Set<CommandType> res = super.accept(cell);
		
		for(CommandType command : res)
		{
			if(!(accept(command, cell) == accept(cell).contains(cell)))
				throw new PostconditionError("CommandAccepterContract -> accept : a command should not be accepted");
		}
		
		return res;
	}
	
	
	public void checkInvariant() {
		
	}

}
