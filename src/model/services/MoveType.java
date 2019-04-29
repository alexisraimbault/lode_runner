package model.services;

public enum MoveType
{
	LEFT,
	RIGHT,
	DOWN,
	UP;
	
	public MoveType opposite()
	{
		switch(this)
		{
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case DOWN:
			return UP;
		case UP:
			return DOWN;
		default:
			break;
		}
		assert false;
		return null;
	}
}
