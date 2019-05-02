package model.services;

public interface IStopAtBorderMoveAccepter<Entity extends IEntity> extends ICommandAccepter<Entity, MoveType>
{
	/*
	 * post:
	 * 	match type
	 * 		
	 */
	public boolean accept(MoveType type, Entity entity);
}
