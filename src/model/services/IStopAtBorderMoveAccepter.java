package model.services;


public interface IStopAtBorderMoveAccepter<Character extends ICharacter> extends ICommandAccepter<Character, MoveType>
{
	/*
	 * post:
	 * 		@result = Cell.hasNext(character, type)
	 */
	public boolean accept(MoveType type, Character entity);
	
}
