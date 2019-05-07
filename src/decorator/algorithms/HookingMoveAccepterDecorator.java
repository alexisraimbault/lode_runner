package decorator.algorithms;

import java.util.Set;

import model.services.ICharacter;
import model.services.IHookingMoveAccepter;
import model.services.MoveType;

public class HookingMoveAccepterDecorator<Character extends ICharacter> implements IHookingMoveAccepter<Character>{
	IHookingMoveAccepter<Character> delegate;
	public HookingMoveAccepterDecorator(IHookingMoveAccepter<Character> d){
		delegate = d;
	}
	public boolean accept(MoveType type, Character cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(Character cell) {
		return delegate.accept(cell);
	}
}
