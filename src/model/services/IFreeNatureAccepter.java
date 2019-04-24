package model.services;

public interface IFreeNatureAccepter extends ICommandAccepter<ICharacter, MoveType>
{
	/*
	 * post:
	 * 	accept(type, character) = environment.getCellNature(x, y).isPlenty()
	 */
}
