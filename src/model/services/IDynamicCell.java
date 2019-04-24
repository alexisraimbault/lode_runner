package model.services;

public interface IDynamicCell extends ICell
{
	/*
	 * pre:
	 * 	0 <= x <= getEnvironment().getWidth() - 1
	 * 	0 <= y <= getEnvironment().getHeight() - 1
	 * 
	 * post:
	 * 	getX() = x
	 * 	getY() = y
	 * 	getEnvironment() = getEnvironment()@before
	 */
	void setPosition(int x, int y);
	
	/*
	 * setX(x) := setPosition(x, getY())
	 */
	void setX(int x);
	
	/*
	 * setY(y) := setPosition(getX(), y)
	 */
	void setY(int y);
}
