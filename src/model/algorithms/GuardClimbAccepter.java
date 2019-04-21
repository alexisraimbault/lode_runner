package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.ClimbType;
import model.services.IEnvironment;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;

public class GuardClimbAccepter implements IGuardClimbAccepter
{

	@Override
	public Set<ClimbType> accept(IGuard entity)
	{
		Set<ClimbType> accepted = EnumSet.noneOf(ClimbType.class);
		
		for(ClimbType type : ClimbType.values())
		{
			if(accept(type, entity))
				accepted.add(type);
		}
		return accepted;
	}
	
	public boolean accept(ClimbType type, IGuard guard)
	{
		IEnvironment environment = guard.getEnvironment();
		switch(type)
		{
		case CLIMBLEFT:
		{
			
		}
		case CLIMBRIGHT:
		{
			
		}
		default:
			break;
		}
		return false;
	}

}
