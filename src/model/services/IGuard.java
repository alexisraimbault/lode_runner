package model.services;

public interface IGuard extends IOperationEntity<GuardCommandType>
{
	/*
	 * init:
	 * 	!isBlocked()
	 */
	
	public boolean isBlocked();
	
	public boolean isWaiting();
	
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
	 * pre:
	 * 	!isBlocked()
	 * 	isWaiting()
	 * 
	 * post:
	 * 	!isBlocked()
	 * 	!isWaiting()
	 */
	public void escape();
	
	/*
	 * invariants:
	 * 	getType() == EntityType.GUARD
	 * 	isWaiting() => !isBlocked()
	 */
}
