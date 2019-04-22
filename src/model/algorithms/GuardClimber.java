package model.algorithms;

import model.services.ClimbType;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;
import model.services.IGuardClimber;

public class GuardClimber implements IGuardClimber
{
	private IGuardClimbAccepter accepter;
	
	public GuardClimber(IGuardClimbAccepter accepter)
	{
		this.accepter = accepter;
	}
	
	public GuardClimber()
	{
		this(new GuardClimbAccepter());
	}
	
	@Override
	public IGuardClimbAccepter getAccepter()
	{
		return accepter;
	}
	
	@Override
	public void climb(ClimbType type, IGuard guard)
	{
		int x = guard.getX();
		int y = guard.getY();
		
		switch(type)
		{
		case CLIMBLEFT:
			guard.setPosition(x - 1, y + 1);
		case CLIMBRIGHT:
			guard.setPosition(x + 1, y + 1);
		default:
			break;
		
		}
	}

}
