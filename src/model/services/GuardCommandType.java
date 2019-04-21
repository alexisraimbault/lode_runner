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
	
	CharacterMoveType moveType()
	{
		switch(this)
		{

		case DOWN:
			return CharacterMoveType.DOWN;
		case LEFT:
			return CharacterMoveType.LEFT;
		case NEUTRAL:
			return CharacterMoveType.NEUTRAL;
		case RIGHT:
			return CharacterMoveType.RIGHT;
		case UP:
			return CharacterMoveType.UP;
		default:
			break;
		}
		assert false;
		return CharacterMoveType.DOWN;
	}
}
