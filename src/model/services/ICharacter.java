package model.services;

public interface ICharacter extends IEntity
{
	/*
	 * post:
	 * 	getCell() = cell
	 */
	public void setCell(ICell cell);
	
	/*
	 * post:
	 * 	getEnvironment() = getEnvironment()@before
	 * 	getX() = x
	 * 	getY() = y
	 */
	public void setPosition(int x, int y);
	
	/*
	 * post:
	 * 	getEnvironment() = getEnvironment()@before
	 * 	getX() = x
	 * 	getY() = getY()@before
	 */
	public void setX(int x);
	
	/*
	 * post:
	 * 	getEnvironment() = getEnvironment()@before
	 * 	getX() = getX()@before
	 * 	getY() = y
	 */
	public void setY(int y);
}
