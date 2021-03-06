package decorator.algorithms;

import java.util.Set;
import java.util.function.Predicate;

import model.services.ICell;
import model.services.ICharacter;
import model.services.IWalkingMoveAccepter;
import model.services.MoveType;

public class WalkingMoveAccepterDecorator<Character extends ICharacter> implements IWalkingMoveAccepter<Character>{
	protected IWalkingMoveAccepter<Character> delegate;
	public WalkingMoveAccepterDecorator(IWalkingMoveAccepter<Character> d){
		delegate = d;
	}
	public boolean accept(MoveType type, Character cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(Character cell) {
		return delegate.accept(cell);
	}
	public Predicate<ICell> getPredicate() {
		return delegate.getPredicate();
	}
}
