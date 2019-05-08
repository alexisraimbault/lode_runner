package decorator.algorithms;

import java.util.Set;
import java.util.function.Predicate;

import model.services.ICell;
import model.services.ICharacter;
import model.services.INoPlentyMoveAccepter;
import model.services.MoveType;

public class NoPlentyMoveAccepterDecorator<Character extends ICharacter> implements INoPlentyMoveAccepter<Character>{
	protected INoPlentyMoveAccepter<Character> delegate;
	public NoPlentyMoveAccepterDecorator(INoPlentyMoveAccepter<Character> d){
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
