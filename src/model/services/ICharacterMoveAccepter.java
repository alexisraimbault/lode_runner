package model.services;

public interface ICharacterMoveAccepter extends ICommandAccepter<ICharacter, CharacterMoveType>
{
	/*
	 * symbols:
	 * 	entity:
	 * 		const
	 */
	
	/*
	 * post:
	 * (entity.getX() == 0) => @result == false
	 */
	public boolean acceptLeft(ICharacter character);
	
	/*
	 * post:
	 * 	(entity.getX() == entity.getEnvironment().getWidth() - 1) => @result == false
	 */
	public boolean acceptRight(ICharacter character);
	
	/*
	 * post:
	 * 	(entity.getY() == 0) => @result == false
	 */
	public boolean acceptDown(ICharacter character);
	
	/*
	 * post:
	 * 	(entity.getY() == entity.getEnvironment().getHeight() - 1) => @result == false
	 */
	public boolean acceptUp(ICharacter character);
}
