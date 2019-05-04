package decorator.algorithms;

import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.ICommandApplier;
import model.services.IShortestPathCalculator;
import model.services.IShortestPathDecision;

public class ShortestPathDecisionDecorator<Character extends ICharacter, CommandType extends Enum<CommandType>> implements IShortestPathDecision<Character, CommandType>{
	protected IShortestPathDecision<Character, CommandType> delegate;
	
	public ShortestPathDecisionDecorator(IShortestPathDecision<Character, CommandType> d) {
		delegate = d;
	}

	public ICommandAccepter<Character, CommandType> getAccepter() {
		return delegate.getAccepter();
	}

	public ICommandApplier<Character, CommandType> getApplier() {
		return delegate.getApplier();
	}

	public CommandType getCommand(Character entity) {
		return delegate.getCommand(entity);
	}

	public IShortestPathCalculator<Character, CommandType> getCalculator() {
		return delegate.getCalculator();
	}
}
