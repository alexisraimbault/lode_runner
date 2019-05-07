package decorator.algorithms;

import java.util.Set;

import model.services.ICharacter;
import model.services.IIgnoringHoleMoveAccepter;
import model.services.MoveType;

public class IgnoringHoleMoveAccepterDecorator<Character extends ICharacter>  implements IIgnoringHoleMoveAccepter<Character>{
	IIgnoringHoleMoveAccepter<Character> delegate;
	public IgnoringHoleMoveAccepterDecorator(IIgnoringHoleMoveAccepter<Character> d){
		delegate = d;
	}
	public boolean accept(MoveType type, Character cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(Character cell) {
		return delegate.accept(cell);
	}
}
