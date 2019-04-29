package model.services;

import java.util.List;

public interface IHole extends ICell, IExecutedOperation
{
	/*
	 * pre:
	 * 	isEnded()
	 * 	getNature() == Nature.HOLE
	 */
	public void fill();
	/*
	 * post:
	 * 	getNature() == Nature.PLATFORM
	 * 	hasTrappedGuard() => !getTrappedGuard().hasInstance()
	 */

	void trap(IGuardSummoner trapped_guard);

	boolean hasTrappedGuard();

	IGuardSummoner getTrappedGuard();
}
