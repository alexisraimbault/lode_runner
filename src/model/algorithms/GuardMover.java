package model.algorithms;

public class GuardMover extends CharacterMover
{

	public GuardMover()
	{
		super(new GuardMovingPolicy());
	}

}
