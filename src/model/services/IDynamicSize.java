package model.services;

public interface IDynamicSize
{
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * false
	 */
	public int getWidth();
	
	/*
	 * pre:
	 * true
	 * 
	 * post:
	 * false
	 */
	public int getHeight();
	
	/*
	 * pre:
	 * width >= 0 && height >= 0
	 * 
	 * post:
	 * getWidth() == width && getHeight() == height
	 */
	public void resize(int width, int height);
	
	/*
	 * invariants
	 * getWidth() >= 0
	 * getHeight >= 0
	 */
}
