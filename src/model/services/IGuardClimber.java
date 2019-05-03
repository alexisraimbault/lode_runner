package model.services;

public interface IGuardClimber extends ICommandApplier<IGuard, ClimbType>
{
	/*
	 * invariants:
	 * 	getAccepter() is IGuardClimbAccepter
	 */
}
