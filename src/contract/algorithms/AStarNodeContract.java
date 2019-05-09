package contract.algorithms;

import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.AStarNodeDecorator;
import model.services.IAStarNode;

public class AStarNodeContract<CommandType extends Enum<CommandType>> extends  AStarNodeDecorator<CommandType>{

	public AStarNodeContract(IAStarNode<CommandType> d) {
		super(d);
		checkInvariant();
	}
	
	
	/*
	 * set:
	 * 	distance := distance(getCell(), getTarget())
	 * 	meetingCharacter := getCell().getContent().nbCharacters() > 1
	 * 	meetingItem := getCell().getContent().nbItems() > 0
	 * post:
	 * 	meetingCharacter =>
	 * 		meetingItem =>
	 * 			getHeuristic() == distance + 30 - 3
	 * 		!meetingItem =>
	 * 			getHeuristic() == distance + 30
	 * 	!meetingCharacter =>
	 * 		meetingItem =>
	 * 			getHeuristic() == max(distance - 3, 0)
	 * 		!meetingItem =>
	 * 			getHeuristic() == distance
	 */
	public int getHeuristic(){
		
		double distance = Math.sqrt(Math.pow((getCell().getX() - getTarget().getX()), 2) + Math.pow((getCell().getY() - getTarget().getY()), 2)) ;
		boolean meetingCharacter = getCell().getContent().nbCharacters() > 1;
		boolean meetingItem = getCell().getContent().nbItems() > 0 ;
		checkInvariant();
		int res = super.getHeuristic();
		checkInvariant();
		if((meetingCharacter && meetingItem && !(getHeuristic() == distance + 30 - 3)))
			throw new PostconditionError("AStarNode -> getHeuristic :meeting a Character and item but getHeuristic() is not incrementing as espected ( +30 - 3)");
		if((meetingCharacter && !meetingItem && !(getHeuristic() == distance + 30 )))
			throw new PostconditionError("AStarNode -> getHeuristic :meeting a Character and no item but getHeuristic() is not incrementing as espected ( +30 )");
		if((!meetingCharacter && meetingItem && !(getHeuristic() == Math.max(distance - 3, 0) )))
			throw new PostconditionError("AStarNode -> getHeuristic :meeting no Character but an item and getHeuristic() is not changing as espected ( max(distance - 3, 0) )");
		if((!meetingCharacter && !meetingItem && !(getHeuristic() == distance )))
			throw new PostconditionError("AStarNode -> getHeuristic :meeting no Character and no item but getHeuristic() is not staying constant");
		return res;
	}

	
	/*
	 * pre:
	 * 	hasPred()
	 */
	public IAStarNode<CommandType> getPred(){
		if(!hasPred())
			throw new PreconditionError("AStarNode -> getPred :should have a predecessor to invoke that method");
		return delegate.getPred();
	}
	
	/*
	 * pre:
	 * 	hasPred()
	 */
	public CommandType getCommandType(){
		if(!hasPred())
			throw new PreconditionError("AStarNode -> getCommandType :should have a predecessor to invoke that method");
		return super.getCommandType();
	}
	
	
	/*
	 * invariants:
	 * 	getCost() >= 0
	 * 	getHeuristic() >= 0
	 * 	getWeight() = getCost() + getHeuristic()
	 * 	!hasPred() <=> getPath().isEmpty()
	 */
	public void checkInvariant() {
		if(!(getCost() >= 0))
			throw new InvariantError("AStarNode : cost should be >= 0");
		if(!(getHeuristic() >= 0))
			throw new InvariantError("AStarNode : heuristic should be >= 0");
		if(!(getWeight() == getCost() + getHeuristic()))
			throw new InvariantError("AStarNode : weight should be equivalent to addding cost and heuristic");
		if(!hasPred() == getPath().isEmpty())
			throw new InvariantError("AStarNode : !hasPred() should be equivalent to getPath().isEmpty()");
	}

}
