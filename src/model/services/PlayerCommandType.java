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
	
	static public PlayerCommandType get(MoveType type)
	{
		switch(type)
		{
		case LEFT:
			return PlayerCommandType.LEFT;
		case RIGHT:
			return PlayerCommandType.RIGHT;
		case DOWN:
			 return PlayerCommandType.DOWN;
		case UP:
			 return PlayerCommandType.UP;
		case NEUTRAL:
			 return PlayerCommandType.NEUTRAL;
		default:
			break;
		}
		return null;
	}
	
	static public PlayerCommandType get(DigType type)
	{
		switch(type)
		{
		case DIGLEFT:
			return PlayerCommandType.DIGLEFT;
		case DIGRIGHT:
			return PlayerCommandType.DIGRIGHT;
		default:
			break;
		}
		return null;
	}
	
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
	
	public boolean isDigType()
	{
		return !isMoveType();
	}
	
	public DigType digType()
	{
		switch(this)
		{
		case DIGLEFT:
			return DigType.DIGLEFT;
		case DIGRIGHT:
			return DigType.DIGRIGHT;
		default:
			break;
		}
		assert false;
		return null;
	}
}
