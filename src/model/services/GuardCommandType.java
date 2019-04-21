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
	
	public boolean isMoveType()
	{
		switch(this)
		{
		case LEFT:
			return true;
		case RIGHT:
			return true;
		case DOWN:
			return true;
		case UP:
			return true;
		case NEUTRAL:
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
		case LEFT:
			return MoveType.LEFT;
		case RIGHT:
			return MoveType.RIGHT;
		case DOWN:
			return MoveType.DOWN;
		case UP:
			return MoveType.UP;
		case NEUTRAL:
			return MoveType.NEUTRAL;
		default:
			break;
		}
		assert false;
		return null;
	}
	
	public boolean isClimbType()
	{
		return !isMoveType();
	}
	
	public ClimbType digType()
	{
		switch(this)
		{
		case CLIMBLEFT:
			return ClimbType.CLIMBLEFT;
		case CLIMBRIGHT:
			return ClimbType.CLIMBRIGHT;
		default:
			break;
		}
		assert false;
		return null;
	}
}
