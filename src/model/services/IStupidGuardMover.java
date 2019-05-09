package model.services;

public interface IStupidGuardMover extends IMover<IGuard>
{
	IStupidGuardMoveAccepter getAccepter();
}
