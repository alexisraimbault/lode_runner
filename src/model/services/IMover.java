package model.services;

public interface IMover<Character extends ICharacter>
{
	public ICommandAccepter<Character, MoveType> getAccepter();
	
	/*
	 * pre:
	 * 	getAccepter().accept(type, character.getCell())
	 * 
	 * post:
	 * 	character.getCell() = next(type, character)
	 */
	public void move(MoveType type, Character character);
	
	public ICell next(MoveType type, Character character);
}
