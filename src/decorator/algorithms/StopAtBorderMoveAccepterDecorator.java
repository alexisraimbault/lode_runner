package decorator.algorithms;

import java.util.Set;

import model.services.IEntity;
import model.services.IStopAtBorderMoveAccepter;
import model.services.MoveType;

public class StopAtBorderMoveAccepterDecorator<Entity extends IEntity> implements IStopAtBorderMoveAccepter<Entity>{
	IStopAtBorderMoveAccepter<Entity> delegate;
	public StopAtBorderMoveAccepterDecorator(IStopAtBorderMoveAccepter<Entity> d){
		delegate = d;
	}
	public boolean accept(MoveType type, Entity entity) {
		return delegate.accept(type, entity);
	}
	public Set<MoveType> accept(Entity cell) {
		return delegate.accept(cell);
	}
}
