package model.services;

public interface ICharacterMover
{
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * true
	 */
	public ICharacterMoveAccepter getPolicy();
	
	/*
	 * pre:
	 * true
	 * 
	 * set:
	 * acceptLeft := getPolicy().acceptLeft(character)
	 * 
	 * post:
	 * acceptLeft => character.getX() == before.character.getX() - 1
	 * !acceptLeft => character.getX() == before.character.getX()
	 * getY() == before.character.getY()
	 */
	public void moveLeft(ICharacter character);
	
	/*
	 * pre:
	 * true
	 * 
	 * set:
	 * acceptRight := getPolicy().acceptRight(character)
	 * 
	 * post:
	 * acceptRight => character.getX() == before.character.getX() + 1
	 * !acceptRight => character.getX() == before.character.getX()
	 * getY() == before.character.getY()
	 */
	public void moveRight(ICharacter character);
	
	/*
	 * pre:
	 * true
	 * 
	 * set:
	 * acceptDown := getPolicy().acceptDown(id)
	 * 
	 * post:
	 * acceptDown => character.getY() == before.character.getY() - 1
	 * !acceptDown => character.getY() == before.character.getY()
	 * getX() == before.character.getX()
	 */
	public void moveDown(ICharacter character);
	
	/*
	 * pre:
	 * true
	 * 
	 * set:
	 * acceptUp := getPolicy().acceptUp(id)
	 * 
	 * post:
	 * acceptUp => character.getY() == before.character.getY() + 1
	 * !acceptUp => character.getY() == before.character.getY()
	 * getX() == before.character.getX()
	 */
	public void moveUp(ICharacter character);
}
