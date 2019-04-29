package model.algorithms;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import model.services.ICommandAccepter;
import model.services.IFallingMoveAccepter;
import model.services.IGuard;
import model.services.IHookingMoveAccepter;
import model.services.INoPlentyMoveAccepter;
import model.services.IPlayer;
import model.services.IPlayerMoveAccepter;
import model.services.IStopAtBorderMoveAccepter;
import model.services.IWalkingMoveAccepter;
import model.services.IWalkingOnCharacterMoveAccepter;
import model.services.MoveType;

public class PlayerMoveAccepter extends DeducingAccepter<IPlayer, MoveType> implements IPlayerMoveAccepter
{
	private IStopAtBorderMoveAccepter<IPlayer> stop_at_border = new StopAtBorderMoveAccepter<>();
	private INoPlentyMoveAccepter<IPlayer> no_plenty = new NoPlentyMoveAccepter<>(new PlentyTester());
	private IHookingMoveAccepter<IPlayer> hooking = new HookingMoveAccepter<>();
	private IWalkingMoveAccepter<IPlayer> walking = new WalkingMoveAccepter<>(new PlentyAndCharacterTester());
	private IFallingMoveAccepter<IPlayer> falling = new FallingMoveAccepter<>(new PlentyAndCharacterTester());
	
	public PlayerMoveAccepter()
	{
		super(MoveType.class);
	}

	@Override
	public boolean accept(MoveType type, IPlayer entity)
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
