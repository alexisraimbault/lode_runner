package decorator.algorithms;

import java.util.Set;

import model.services.IPlayer;
import model.services.IPlayerMoveAccepter;
import model.services.MoveType;

public class PlayerMoveAccepterDecorator  implements IPlayerMoveAccepter{
	IPlayerMoveAccepter delegate;
	public PlayerMoveAccepterDecorator(IPlayerMoveAccepter d){
		delegate = d;
	}
	public boolean accept(MoveType type, IPlayer cell) {
		return delegate.accept(type, cell);
	}
	public Set<MoveType> accept(IPlayer cell) {
		return delegate.accept(cell);
	}
}
