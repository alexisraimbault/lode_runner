package model.services;

public interface IBlockingHoleMoveAccepter
	<Character extends ICharacter>
		extends ICommandAccepter<Character, MoveType>
{

}
