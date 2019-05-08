package decorator.algorithms;

import java.util.Set;

import model.services.ICharacter;
import model.services.IEntity;
import model.services.IStopAtBorderMoveAccepter;
import model.services.MoveType;

public class StopAtBorderMoveAccepterDecorator<Character extends ICharacter> implements IStopAtBorderMoveAccepter<Character>{
	protected IStopAtBorderMoveAccepter<Character> delegate;
	public StopAtBorderMoveAccepterDecorator(IStopAtBorderMoveAccepter<Character> d){
		delegate = d;
	}
	public boolean accept(MoveType type, Character entity) {
		return delegate.accept(type, entity);
	}
	public Set<MoveType> accept(Character cell) {
		return delegate.accept(cell);
	}
}
