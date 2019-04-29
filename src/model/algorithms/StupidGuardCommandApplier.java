package model.algorithms;

import model.services.GuardCommandType;
import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IGuardClimber;
import model.services.IGuardCommandAccepter;
import model.services.IMover;
import model.services.IStupidGuardCommandAccepter;
import model.services.IStupidGuardCommandApplier;

public class StupidGuardCommandApplier implements IStupidGuardCommandApplier
{
	private IMover<IGuard> mover;
	private IGuardClimber climber;
	private IStupidGuardCommandAccepter accepter;
	
	public StupidGuardCommandApplier(IMover<IGuard> mover, IGuardClimber climber, IStupidGuardCommandAccepter accepter)
	{
		this.mover = mover;
		this.climber = climber;
		this.accepter = accepter;
	}
	
	public StupidGuardCommandApplier()
	{
		this(new Mover<>(new StupidGuardMoveAccepter()), new GuardClimber(), new StupidGuardCommandAccepter());
	}

	@Override
	public ICommandAccepter<IGuard, GuardCommandType> getAccepter()
	{
		return accepter;
	}

	@Override
	public void apply(GuardCommandType type, IGuard guard)
	{
		if(type.isMoveType())
		{
			mover.apply(type.moveType(), guard);
		}
		else if(type.isClimbType())
		{
			climber.apply(type.climbType(), guard);
		}
	}
}
