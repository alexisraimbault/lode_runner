package model.algorithms;

public class PlayerMover extends CharacterMover
{

	public PlayerMover()
	{
		super(new PlayerCommandAccepter());
	}
	
}
