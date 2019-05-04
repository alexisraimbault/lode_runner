package contract.algorithms;

import contract.contracterr.InvariantError;
import decorator.algorithms.ShortestPathDecisionDecorator;
import model.services.ICharacter;
import model.services.IShortestPathDecision;

public class ShortestPathDecisionContract<Character extends ICharacter, CommandType extends Enum<CommandType>> extends ShortestPathDecisionDecorator<Character,CommandType> {

	public ShortestPathDecisionContract(IShortestPathDecision<Character, CommandType> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	/*
	 * post set:
	 * 	path := getCalculator().getPath(source, getTarget(), getAccepter())
	 * post:
	 * 	!(path == null || path.isEmpty()) => @result = path.get(0)
	 */
	//CommandType getCommand(Character character);

	public void checkInvariant() {
		if(!(getApplier().getAccepter() == getAccepter()))
			throw new InvariantError("ShortestPathDecision : the the same accepter for Applier and Decision");
	}
}
