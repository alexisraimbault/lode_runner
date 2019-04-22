package model.algorithms;

import java.util.Random;
import java.util.Set;

import model.services.GuardCommandType;
import model.services.ICommandAccepter;
import model.services.IGuard;
import model.services.IGuardCommandAccepter;
import model.services.IGuardDecision;

public class RandomGuardDecision implements IGuardDecision
{
	private IGuardCommandAccepter accepter;
	private Random rand;
	
	public RandomGuardDecision(IGuardCommandAccepter accepter)
	{
		this.accepter = accepter;
		this.rand = new Random();
	}

	@Override
	public ICommandAccepter<IGuard, GuardCommandType> getAccepter()
	{
		return accepter;
	}

	@Override
	public GuardCommandType getCommand(IGuard guard)
	{
		Set<GuardCommandType> accepted = accepter.accept(guard);
		return (GuardCommandType) accepted.toArray()[Math.abs(rand.nextInt()) % accepted.size()];
	}
}
