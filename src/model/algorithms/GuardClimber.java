package model.algorithms;

import model.services.IContent;
import model.services.IEnvironment;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;
import model.services.IGuardClimber;
import model.services.IGuardMoveAccepter;
import model.services.Nature;

public class GuardClimber implements IGuardClimber
{
	private IGuardClimbAccepter accepter;
	
	public GuardClimber(IGuardClimbAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	@Override
	public IGuardClimbAccepter getAccepter()
	{
		return accepter;
	}
	
	@Override
	public void climbLeft(IGuard guard)
	{
		int x = guard.getX();
		int y = guard.getY();
		
		guard.setPosition(x - 1, y + 1);
	}

	@Override
	public void climbRight(IGuard guard)
	{
		int x = guard.getX();
		int y = guard.getY();
		
		guard.setPosition(x + 1, y + 1);
	}

}
