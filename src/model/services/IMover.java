package model.services;

public interface IMover<Character extends ICharacter> extends ICommandApplier<Character, MoveType>
{
	/*
	 * post:
	 * 	match type
	 * 		MoveType.LEFT =>
	 * 			post character.setX(getX() - 1)
	 * 		MoveType.RIGHT =>
	 * 			post character.setX(getX() + 1)
	 * 		MoveType.DOWN =>
	 * 			post character.setY(getY() - 1)
	 * 		MoveType.UP =>
	 * 			post character.setY(getY() + 1)
	 */
}
