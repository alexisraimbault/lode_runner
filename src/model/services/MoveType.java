package model.services;

public enum MoveType
{
	LEFT,
	RIGHT,
	DOWN,
	UP,
	NEUTRAL;
	
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
		case NEUTRAL:
			return NEUTRAL;
		default:
			break;
		}
		assert false;
		return MoveType.NEUTRAL;
	}
}
