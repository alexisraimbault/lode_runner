package model.services;

public interface IStaticEntity extends IEntity
{
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * true
	 */
	public int getX();
	
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * true
	 */
	public int getY();
	
	/*
	 * invariants:
	 * getX() >= 0 && getX() <= getEnvironment().getWidth() - 1
	 * getY() >= 0 && getY() <= getEnvironment().getHeight() - 1
	 * getX() const
	 * getY() const
	 */
}
