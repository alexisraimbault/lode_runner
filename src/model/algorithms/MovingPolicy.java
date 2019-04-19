package model.algorithms;

import model.services.ICharacter;
import model.services.ICharacterMovingPolicy;

public class MovingPolicy implements ICharacterMovingPolicy
{

	@Override
	public boolean acceptLeft(ICharacter character)
	{
		return character.getX() > 0;
	}

	@Override
	public boolean acceptRight(ICharacter character)
	{
		return character.getX() < character.getEnvironment().getWidth() - 1;
	}
	
	@Override
	public boolean acceptDown(ICharacter character)
	{
		return character.getY() > 0;
	}
	
	@Override
	public boolean acceptUp(ICharacter character)
	{
		return character.getY() < character.getEnvironment().getHeight() - 1;
	}

}
