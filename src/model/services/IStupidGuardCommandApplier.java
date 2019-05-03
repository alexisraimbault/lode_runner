package model.services;

public interface IStupidGuardCommandApplier extends ICommandApplier<IGuard, GuardCommandType>
{
	IStupidGuardCommandAccepter getAccepter();
}
