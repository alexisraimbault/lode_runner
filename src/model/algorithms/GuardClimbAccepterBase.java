package model.algorithms;

import java.util.function.Predicate;

import model.gamestate.entities.Cell;
import model.services.ClimbType;
import model.services.ICell;
import model.services.IGuard;
import model.services.IGuardClimbAccepterBase;
import model.services.MoveType;

public class GuardClimbAccepterBase extends DeducingAccepter<IGuard, ClimbType> implements IGuardClimbAccepterBase
{
	
	private Predicate<ICell> plenty_tester;
	
	public GuardClimbAccepterBase(Predicate<ICell> plenty_tester)
	{
		super(ClimbType.class);
		this.plenty_tester = plenty_tester;
	}

	@Override
	public boolean accept(ClimbType type, IGuard guard)
	{
		if(guard.getY() == guard.getEnvironment().getHeight() - 1)
			return false;
		
		ICell up_cell = Cell.getNext(guard, MoveType.UP);
		
		if(plenty_tester.test(up_cell))
			return false;
		
		switch(type)
		{
		case CLIMBLEFT:
		{
			if(guard.getX() == 0)
				return false;
			
			ICell corner_cell = Cell.getNext(up_cell, MoveType.LEFT);
			
			if(plenty_tester.test(corner_cell))
				return false;
			
			ICell next_cell = Cell.getNext(guard, MoveType.LEFT);
			
			if(!plenty_tester.test(next_cell))
				return false;
			
			return true;
		}
		case CLIMBRIGHT:
		{

			if(guard.getX() == guard.getEnvironment().getWidth() - 1)
				return false;

			ICell corner_cell = Cell.getNext(up_cell, MoveType.RIGHT);
			
			if(plenty_tester.test(corner_cell))
				return false;
			
				ICell next_cell = Cell.getNext(guard, MoveType.RIGHT);
			
			if(!plenty_tester.test(next_cell))
				return false;
			
			return true;
		}
		default:
			break;
		}
		return false;
	}

	@Override
	public Predicate<ICell> getPlentyTester()
	{
		return plenty_tester;
	}

}
