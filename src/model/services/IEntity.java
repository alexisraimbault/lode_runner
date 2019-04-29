package model.services;

public interface IEntity extends ICell
{
	public EntityType getType();
	
	/*
	 * post:
	 * 	getEnvironment() = cell.getEnvironment()
	 * 	getX() = cell.getX()
	 * 	getY() = cell.getY()
	 */
	public void setPosition(ICell cell);
	
	/*
	 * post:
	 * 	getX() = x
	 * 	getY() = y
	 */
	public void setPosition(int x, int y);
	
	/*
	 * post:
	 * 	getX() = x
	 */
	public void setX(int x);
	
	/*
	 * post:
	 * 	getY() = y
	 */
	public void setY(int y);
	
	/*
	 * invariants:
	 * 	getContent().contains(getType())
	 */
	
}
