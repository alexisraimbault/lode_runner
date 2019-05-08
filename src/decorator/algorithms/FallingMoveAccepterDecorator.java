package decorator.algorithms;

import java.util.Set;
import java.util.function.Predicate;

import model.services.ICell;
import model.services.ICharacter;
import model.services.IFallingMoveAccepter;
import model.services.MoveType;

public class FallingMoveAccepterDecorator<Character extends ICharacter> implements IFallingMoveAccepter<Character>{
	protected IFallingMoveAccepter<Character> delegate;
	public FallingMoveAccepterDecorator(IFallingMoveAccepter<Character> d){
		delegate = d;
	}
	public boolean accept(MoveType type, Character cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(Character cell) {
		return delegate.accept(cell);
	}
	@Override
	public Predicate<ICell> getPlentyTester() {
		return delegate.getPlentyTester();
	}
}
