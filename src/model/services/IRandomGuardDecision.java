package model.services;

public interface IRandomGuardDecision extends IRandomDecision<IGuard, GuardCommandType>
{
	IGuardCommandAccepter getAccepter();
}
