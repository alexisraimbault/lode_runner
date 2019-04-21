package model.services;

public interface IDynamicEntity extends IEntity
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
	 * pre:
	 * x >= 0 && x <= getEnvironment().getWidth() - 1
	 * 
	 * post:
	 * getX() == x
	 */
	public void setPosition(int x, int y);
	
	public void setX(int x);
	
	public void setY(int y);
	
	/*
	 * pre:
	 * y >= 0 && y <= getEnvironment().getHeight() - 1
	 * 
	 * post:
	 * getY() == y
	 */
	//public void setY(int y);
	
	/*
	 * invariants:
	 * getX() >= 0 && getX() <= getEnvironment().getWidth() - 1
	 * getY() >= 0 && getY() <= getEnvironment().getHeight() - 1
	 */
}
