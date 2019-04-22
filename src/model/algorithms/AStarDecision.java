package model.algorithms;

import model.services.IAStarDecision;
import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IShortestPathCalculator;
import model.services.MoveType;

public class AStarDecision implements IAStarDecision
{
	private ICharacterMoveAccepter accepter;
	private ICharacter target;
	private IShortestPathCalculator calculator;
	
	public AStarDecision(IShortestPathCalculator calculator, ICharacter target, ICharacterMoveAccepter accepter)
	{
		this.calculator = calculator;
		this.target = target;
		this.accepter = accepter;
	}

	@Override
	public ICharacterMoveAccepter getAccepter()
	{
		return accepter;
	}

	@Override
	public MoveType getCommand(ICharacter character)
	{
		return calculator.getPath(character, target, accepter).get(0);
	}

}
