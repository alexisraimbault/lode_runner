package model.algorithms;

import model.services.ICharacter;
import model.services.ICharacterMover;
import model.services.ICharacterMovingPolicy;

public class CharacterMover implements ICharacterMover
{
	private ICharacterMovingPolicy policy;
	
	public CharacterMover(ICharacterMovingPolicy policy)
	{
		this.policy = policy;
	}

	@Override
	public ICharacterMovingPolicy getPolicy()
	{
		return policy;
	}

	@Override
	public void moveLeft(ICharacter character)
	{
		if(policy.acceptLeft(character))
		{
			character.setX(character.getX() - 1);
		}
	}

	@Override
	public void moveRight(ICharacter character)
	{
		if(policy.acceptRight(character))
		{
			character.setX(character.getX() + 1);
		}
	}

	@Override
	public void moveDown(ICharacter character)
	{
		if(policy.acceptDown(character))
		{
			character.setY(character.getY() - 1);
		}
	}

	@Override
	public void moveUp(ICharacter character)
	{
		if(policy.acceptUp(character))
		{
			character.setY(character.getY() + 1);
		}
	}
}
