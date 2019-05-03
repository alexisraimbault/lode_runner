package model.services;

public interface IGuard extends IOperationEntity<GuardCommandType>
{
	/*
	 * init:
	 * 	!isBlocked()
	 */
	
	public boolean isBlocked();
	
	/*
	 * pre:
	 * 	!isBlocked()
	 */
	public void block(long blocking_time);
	
	/*
	 * pre:
	 * 	isBlocked()
	 */
	public void unblock();
	
	/*
	 * invariants:
	 * 	getType() == EntityType.GUARD
	 */
}
