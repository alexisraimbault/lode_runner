package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.ClimbType;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;
import model.services.Nature;

public class GuardClimbAccepter implements IGuardClimbAccepter
{

	@Override
	public Set<ClimbType> accept(IGuard guard)
	{
		Set<ClimbType> accepted = EnumSet.noneOf(ClimbType.class);
		
		for(ClimbType type : ClimbType.values())
		{
			if(accept(type, guard))
				accepted.add(type);
		}
		return accepted;
	}
	
	public boolean accept(ClimbType type, IGuard guard)
	{
		IEnvironment environment = guard.getEnvironment();
		int x = guard.getX();
		int y = guard.getY();
		
		Nature nature = environment.getCellNature(x, y);
		
		if(nature != Nature.HOLE)
			return false;
		
		int up_x = x;
		int up_y = y + 1;
		Nature up_nature = environment.getCellNature(up_x, up_y);
		IContent up_content = environment.getCellContent(up_x, up_y);
		
		if(up_nature.isPlenty())
			return false;
		
		if(up_content.nbCharacters() > 0)
			return false;
		
		switch(type)
		{
		case CLIMBLEFT:
		{
			int corner_x = up_x - 1;
			int corner_y = up_y;
			Nature corner_nature = environment.getCellNature(corner_x, corner_y);
			IContent corner_content = environment.getCellContent(corner_x, corner_y);
			
			if(corner_nature.isPlenty())
				return false;
			
			if(corner_content.nbCharacters() > 0)
				return false;
			
			return true;
		}
		case CLIMBRIGHT:
		{
			int corner_x = up_x + 1;
			int corner_y = up_y;
			Nature corner_nature = environment.getCellNature(corner_x, corner_y);
			IContent corner_content = environment.getCellContent(corner_x, corner_y);
			
			if(corner_nature.isPlenty())
				return false;
			
			if(corner_content.nbCharacters() > 0)
				return false;
			
			return true;
			
		}
		default:
			break;
		}
		return false;
	}

}
