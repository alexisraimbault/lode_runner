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
