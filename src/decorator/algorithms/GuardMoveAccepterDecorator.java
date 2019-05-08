package decorator.algorithms;

import java.util.Set;

import model.services.IFallingMoveAccepter;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IHookingMoveAccepter;
import model.services.INoPlentyMoveAccepter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.IWalkingMoveAccepter;
import model.services.MoveType;

public class GuardMoveAccepterDecorator implements IGuardMoveAccepter{
	protected IGuardMoveAccepter delegate;
	public GuardMoveAccepterDecorator(IGuardMoveAccepter d){
		delegate = d;
	}
	public boolean accept(MoveType type, IGuard cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(IGuard cell) {
		return delegate.accept(cell);
	}
	public IStopAtBorderMoveAccepter<IGuard> getStop_at_border() {
		return delegate.getStop_at_border();
	}
	public IHookingMoveAccepter<IGuard> getHooking() {
		return delegate.getHooking();
	}
	public INoPlentyMoveAccepter<IGuard> getNo_plenty() {
		return delegate.getNo_plenty();
	}
	public IWalkingMoveAccepter<IGuard> getWalking() {
		return delegate.getWalking();
	}
	public IFallingMoveAccepter<IGuard> getFalling() {
		return delegate.getFalling();
	}

}
