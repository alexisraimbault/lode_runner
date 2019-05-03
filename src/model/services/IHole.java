package model.services;

public interface IHole extends ICell, IExecutedOperation
{
	boolean hasTrappedGuard();
	
	/*
	 * pre:
	 * 	hasTrappedGuard()
	 */
	IGuardSummoner getTrappedGuard();
	
	/*
	 * pre:
	 * 	isEnded()
	 * 	getNature() == Nature.HOLE
	 * 
	 * post:
	 * 	getNature() == Nature.PLATFORM
	 * 	hasTrappedGuard()@before => !getTrappedGuard()@before.hasInstance()
	 */
	public void fill();
	
	/*
	 * pre:
	 * 	!hasTrappedGuard()
	 * 
	 * post:
	 * 	hasTrappedGuard()
	 */
	void trap(IGuardSummoner trapped_guard);
}
