package model.services;

public interface IRandomGuardMoveDecision extends IRandomDecision<IGuard, MoveType>
{
	IGuardMoveAccepter getAccepter();
}
