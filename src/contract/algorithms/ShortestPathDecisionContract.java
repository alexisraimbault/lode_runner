package contract.algorithms;

import decorator.algorithms.ShortestPathDecisionDecorator;
import model.services.ICharacter;
import model.services.IShortestPathDecision;

public class ShortestPathDecisionContract<Character extends ICharacter, CommandType extends Enum<CommandType>> extends ShortestPathDecisionDecorator<Character,CommandType> {

	public ShortestPathDecisionContract(IShortestPathDecision<Character, CommandType> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

}
