package model.services;

public interface IGuardCommandApplier  extends ICommandApplier<IGuard, GuardCommandType>
{
	IGuardCommandAccepter getAccepter();
}
