package model.services;

public interface IStupidGuardMover extends IMoveApplier<IGuard>
{
	IStupidGuardMoveAccepter getAccepter();
}
