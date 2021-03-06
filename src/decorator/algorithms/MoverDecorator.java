package decorator.algorithms;

import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.IMover;
import model.services.MoveType;

public class MoverDecorator<Character extends ICharacter> implements IMover<Character>{
	protected IMover<Character> delegate;
	
	public MoverDecorator(IMover<Character> d) {
		delegate = d;
	}

	public ICommandAccepter<Character, MoveType> getAccepter() {
		return delegate.getAccepter();
	}

	@Override
	public void apply(MoveType type, Character entity) {
		delegate.apply(type, entity);
	}
}