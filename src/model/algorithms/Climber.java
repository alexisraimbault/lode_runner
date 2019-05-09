package model.algorithms;

import model.services.ClimbType;
import model.services.ICharacter;
import model.services.IClimber;
import model.services.IGuardClimber;
import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;

public class Climber implements IClimber
{
	private ICommandAccepter<IGuard, ClimbType> accepter;
	
	public Climber(ICommandAccepter<IGuard, ClimbType> accepter)
	{
		this.accepter = accepter;
	}
	
	public Climber()
	{
		this(new GuardClimbAccepter());
	}
	
	@Override
	public ICommandAccepter<IGuard, ClimbType> getAccepter()
	{
		return accepter;
	}
	
	@Override
	public void apply(ClimbType type, IGuard guard)
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
