package model.services;

public interface IGuardClimber
{
	public IGuardClimbAccepter getAccepter();
	public void climb(ClimbType type, IGuard guard);
}
