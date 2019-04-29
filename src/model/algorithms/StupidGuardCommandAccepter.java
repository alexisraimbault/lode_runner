package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.ClimbType;
import model.services.GuardCommandType;
import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;
import model.services.IStupidGuardClimbAccepter;
import model.services.IStupidGuardCommandAccepter;
import model.services.IStupidGuardMoveAccepter;
import model.services.MoveType;

public class StupidGuardCommandAccepter implements IStupidGuardCommandAccepter
{

	private IStupidGuardMoveAccepter move_accepter;
	private IStupidGuardClimbAccepter climb_accepter;
	
	public StupidGuardCommandAccepter(IStupidGuardMoveAccepter move_accepter, IStupidGuardClimbAccepter climb_accepter)
	{
		this.move_accepter = move_accepter;
		this.climb_accepter = climb_accepter;
	}
	
	public StupidGuardCommandAccepter()
	{
		this(new StupidGuardMoveAccepter(), new StupidGuardClimbAccepter());
	}

	@Override
	public Set<GuardCommandType> accept(IGuard guard)
	{
		Set<GuardCommandType> accepted = EnumSet.noneOf(GuardCommandType.class);
		Set<MoveType> moves = move_accepter.accept(guard);
		Set<ClimbType> climbs = climb_accepter.accept(guard);
		
		for(MoveType type : moves)
			accepted.add(GuardCommandType.get(type));
		for(ClimbType type : climbs)
			accepted.add(GuardCommandType.get(type));
		return accepted;
	}

	@Override
	public boolean accept(GuardCommandType type, IGuard guard)
	{
		if(type.isMoveType())
			return move_accepter.accept(type.moveType(), guard);
		else if(type.isClimbType())
			return climb_accepter.accept(type.climbType(), guard);
		return false;
	}

}
