package model.services;

public enum GuardCommandType
{
	LEFT,
	RIGHT,
	DOWN,
	UP,
	NEUTRAL,
	CLIMBLEFT,
	CLIMBRIGHT;
	
	MoveType moveType()
	{
		switch(this)
		{

		case DOWN:
			return MoveType.DOWN;
		case LEFT:
			return MoveType.LEFT;
		case NEUTRAL:
			return MoveType.NEUTRAL;
		case RIGHT:
			return MoveType.RIGHT;
		case UP:
			return MoveType.UP;
		default:
			break;
		}
		assert false;
		return MoveType.DOWN;
	}
}
