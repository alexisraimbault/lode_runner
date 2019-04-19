package model.services;

public interface IStaticSize
{
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * true
	 */
	public int getWidth();
	
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * true
	 */
	public int getHeight();
	
	/*
	 * invariants:
	 * getWidth() >= 0
	 * getHeight >= 0
	 * getWidth() const
	 * getHeight() const
	 */
}
