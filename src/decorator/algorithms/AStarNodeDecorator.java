package decorator.algorithms;

import java.util.List;

import model.services.IAStarNode;
import model.services.ICell;

public class AStarNodeDecorator<CommandType extends Enum<CommandType>> implements IAStarNode<CommandType> {
	protected  IAStarNode<CommandType> delegate;
	
	public AStarNodeDecorator( IAStarNode<CommandType> d) {
		delegate = d;
	}

	public ICell getCell() {
		return delegate.getCell();
	}

	public int getCost() {
		return delegate.getCost();
	}

	public int getHeuristic() {
		return delegate.getHeuristic();
	}

	public int getWeight() {
		return delegate.getWeight();
	}

	public boolean hasPred() {
		return delegate.hasPred();
	}

	public ICell getTarget() {
		return delegate.getTarget();
	}

	public IAStarNode<CommandType> getPred() {
		return delegate.getPred();
	}

	public CommandType getCommandType() {
		return delegate.getCommandType();
	}

	public List<CommandType> getPath() {
		return delegate.getPath();
	}
}
