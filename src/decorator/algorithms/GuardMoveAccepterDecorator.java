package decorator.algorithms;

import java.util.Set;

import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.MoveType;

public class GuardMoveAccepterDecorator implements IGuardMoveAccepter{
	IGuardMoveAccepter delegate;
	public GuardMoveAccepterDecorator(IGuardMoveAccepter d){
		delegate = d;
	}
	public boolean accept(MoveType type, IGuard cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(IGuard cell) {
		return delegate.accept(cell);
	}

}
