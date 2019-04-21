package model.services;

public enum PlayerCommandType
{
	LEFT,
	RIGHT,
	DOWN,
	UP,
	NEUTRAL,
	DIGLEFT,
	DIGRIGHT;
	
	public boolean isMoveType()
	{
		switch(this)
		{
		case DOWN:
			return true;
		case LEFT:
			return true;
		case NEUTRAL:
			return true;
		case RIGHT:
			return true;
		case UP:
			return true;
		default:
			break;
		}
		return false;
	}
	
	public MoveType moveType()
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
