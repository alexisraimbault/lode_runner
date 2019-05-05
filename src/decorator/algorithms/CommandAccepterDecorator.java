package decorator.algorithms;

import java.util.Set;

import model.services.IAStarNode;
import model.services.ICell;
import model.services.ICommandAccepter;

public class CommandAccepterDecorator<Cell extends ICell,CommandType extends Enum<CommandType>> implements ICommandAccepter<Cell, CommandType> {
	protected  ICommandAccepter<Cell, CommandType> delegate;
	
	public CommandAccepterDecorator( ICommandAccepter<Cell, CommandType> d) {
		delegate = d;
	}

	public boolean accept(CommandType type, Cell cell) {
		return delegate.accept(type, cell);
	}

	public Set<CommandType> accept(Cell cell) {
		return delegate.accept(cell);
	}
}
