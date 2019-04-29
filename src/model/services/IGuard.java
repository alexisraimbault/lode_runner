package model.services;

public interface IGuard extends IOperationEntity<GuardCommandType>
{
	public boolean isBlocked();
	
	public void block(long blocking_time);
	public void unblock();
}
