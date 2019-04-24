package model.algorithms;

import java.util.Random;
import java.util.Set;

import model.services.ICharacter;
import model.services.ICommandAccepter;
import model.services.IRandomDecision;

public class RandomDecision<Character extends ICharacter, CommandType extends Enum<CommandType>> implements IRandomDecision<Character, CommandType>
{
	private ICommandAccepter<Character, CommandType> accepter;
	private Random rand;
	
	public RandomDecision(ICommandAccepter<Character, CommandType> accepter)
	{
		this.accepter = accepter;
		this.rand = new Random();
	}

	@Override
	public ICommandAccepter<Character, CommandType> getAccepter()
	{
		return accepter;
	}

	@Override
	public CommandType getCommand(Character character)
	{
		Set<CommandType> accepted = accepter.accept(character);
		return (CommandType) accepted.toArray()[Math.abs(rand.nextInt()) % accepted.size()];
	}
}
