package model.algorithms;

import model.services.GuardCommandType;
import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IGuardClimber;
import model.services.IGuardCommandAccepter;
import model.services.IGuardMoveAccepter;
import model.services.IMover;
import model.services.IGuardCommandApplier;
import model.services.IPlayer;
import model.services.IPlayerCommandAccepter;
import model.services.PlayerCommandType;

public class GuardCommandApplier implements IGuardCommandApplier
{
	private IMover<IGuard> mover;
	private IGuardClimber climber;
	private IGuardCommandAccepter accepter;
	
	public GuardCommandApplier(IMover<IGuard> mover, IGuardClimber climber, IGuardCommandAccepter accepter)
	{
		this.mover = mover;
		this.climber = climber;
		this.accepter = accepter;
	}
	
	public GuardCommandApplier()
	{
		this(new Mover<>(new GuardMoveAccepter()), new GuardClimber(), new GuardCommandAccepter());
	}

	@Override
	public IGuardCommandAccepter getAccepter()
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
