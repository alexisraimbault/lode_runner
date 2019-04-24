package model.services;

public interface IEntity
{
	public EntityType getType();
	public ICell getCell();
	
	/*
	 * post:
	 * 	getEnvironment() = getCell().getEnvironment()
	 */
	public IEnvironment getEnvironment();
	
	/*
	 * post:
	 * 	getX() = getCell().getX()
	 */
	public int getX();
	
	/*
	 * post:
	 * 	getY() = getCell().getX()
	 */
	public int getY();
	
	/*
	 * invariants:
	 * 	getType() const
	 */
}
