package model.algorithms;

import model.services.IFallingMoveAccepter;
import model.services.IGuard;
import model.services.IGuardMoveAccepter;
import model.services.IHookingMoveAccepter;
import model.services.INoPlentyMoveAccepter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.IWalkingMoveAccepter;
import model.services.IWalkingOnCharacterMoveAccepter;
import model.services.MoveType;

public class GuardMoveAccepter extends DeducingAccepter<IGuard, MoveType> implements IGuardMoveAccepter
{
	
	private IStopAtBorderMoveAccepter<IGuard> stop_at_border = new StopAtBorderMoveAccepter<>();
	private INoPlentyMoveAccepter<IGuard> no_plenty = new NoPlentyMoveAccepter<>(new PlentyAndGuardsTester());
	private IHookingMoveAccepter<IGuard> hooking = new HookingMoveAccepter<>();
	private IWalkingMoveAccepter<IGuard> walking = new WalkingMoveAccepter<>(new PlentyAndCharacterTester());
	private IFallingMoveAccepter<IGuard> falling = new FallingMoveAccepter<>(new PlentyAndCharacterTester());
	
	public GuardMoveAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, IGuard entity)
	{
		if(!stop_at_border.accept(type, entity))
			return false;
		
		if(!no_plenty.accept(type, entity))
			return false;
		
		if(hooking.accept(type, entity))
			return true;
		
		if(walking.accept(type, entity))
			return true;
		
		if(falling.accept(type, entity))
			return true;
		
		return false;
	}
	
}
