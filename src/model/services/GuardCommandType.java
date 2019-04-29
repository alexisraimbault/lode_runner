package model.services;

public enum GuardCommandType
{
	LEFT,
	RIGHT,
	DOWN,
	UP,
	CLIMBLEFT,
	CLIMBRIGHT,
	BLOCKING;
	
	static public GuardCommandType get(MoveType type)
	{
		switch(type)
		{
		case LEFT:
			return GuardCommandType.LEFT;
		case RIGHT:
			return GuardCommandType.RIGHT;
		case DOWN:
			 return GuardCommandType.DOWN;
		case UP:
			 return GuardCommandType.UP;
		default:
			break;
		}
		return null;
	}
	
	static public GuardCommandType get(ClimbType type)
	{
		switch(type)
		{
		case CLIMBLEFT:
			return GuardCommandType.CLIMBLEFT;
		case CLIMBRIGHT:
			return GuardCommandType.CLIMBRIGHT;
		default:
			break;
		}
		return null;
	}
	
	public boolean isMoveType()
	{
		switch(this)
		{
		case LEFT:
		case RIGHT:
		case DOWN:
		case UP:
			return true;
		default:
			return false;
		}
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
		default:
			break;
		}
		assert false;
		return null;
	}
	
	public boolean isClimbType()
	{
		switch(this)
		{
		case CLIMBLEFT:
		case CLIMBRIGHT:
			return true;
		default:
			return false;
		}
	}
	
	public ClimbType climbType()
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
