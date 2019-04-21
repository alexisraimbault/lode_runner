package model.services;

public interface IGuardClimber
{
	public IGuardClimbAccepter getAccepter();
	public void climbLeft(IGuard guard);
	public void climbRight(IGuard guard);
}
