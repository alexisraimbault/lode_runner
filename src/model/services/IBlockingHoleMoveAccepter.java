package model.services;

public interface IBlockingHoleMoveAccepter
	<Character extends ICharacter>
		extends ICommandAccepter<Character, MoveType>
{
	/**
	 * post : @result == (!character.getNature() == Nature.HOLE)
	 * 
	 */
	boolean accept(MoveType type, Character character);
}
