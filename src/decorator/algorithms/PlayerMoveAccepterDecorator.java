package decorator.algorithms;

import java.util.Set;

import model.services.IFallingMoveAccepter;
import model.services.IHookingMoveAccepter;
import model.services.INoPlentyMoveAccepter;
import model.services.IPlayer;
import model.services.IPlayerMoveAccepter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.IWalkingMoveAccepter;
import model.services.MoveType;

public class PlayerMoveAccepterDecorator  implements IPlayerMoveAccepter{
	protected IPlayerMoveAccepter delegate;
	public PlayerMoveAccepterDecorator(IPlayerMoveAccepter d){
		delegate = d;
	}
	public boolean accept(MoveType type, IPlayer cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(IPlayer cell) {
		return delegate.accept(cell);
	}
	public IStopAtBorderMoveAccepter<IPlayer> getStop_at_border() {
		return delegate.getStop_at_border();
	}
	public IHookingMoveAccepter<IPlayer> getHooking() {
		return delegate.getHooking();
	}
	public INoPlentyMoveAccepter<IPlayer> getNo_plenty() {
		return delegate.getNo_plenty();
	}
	public IWalkingMoveAccepter<IPlayer> getWalking() {
		return delegate.getWalking();
	}
	public IFallingMoveAccepter<IPlayer> getFalling() {
		return delegate.getFalling();
	}
}
