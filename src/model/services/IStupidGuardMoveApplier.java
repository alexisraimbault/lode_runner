package model.services;

public interface IStupidGuardMoveApplier extends IMoveApplier<IGuard>
{
	IStupidGuardMoveAccepter getAccepter();
}
