package model.services;

public interface IDynamicScreen
{
	public int getWidth();
	public int getHeight();
	/**
	 * init : 
	 * forall x in[0, getWidth() -1]
	 * 		forall y in[0, getHeight() -1 ]
	 * 			getCellNature(x,y) == Nature.EMPTY
	 */
	
	/*
	 * pre:
	 * 	0 <= x <= getWidth() - 1
	 * 	0 <= y <= getHeight() - 1
	 */
	public Nature getCellNature(int x, int y);
	
	/*
	 * pre:
	 * 	0 <= x <= getWidth() - 1
	 * 	0 <= y <= getHeight() - 1
	 * 
	 * post:
	 * 	getCellNature(x, y) == nature
	 */
	public void setCellNature(int x, int y, Nature nature);
	
	/*
	 * pre:
	 * width >= 0 && height >= 0
	 * 
	 * post:
	 * getWidth() == width && getHeight() == height
	 */
	public void resize(int width, int height);

	
	/*
	 * invariants:
	 * getWidth() >= 0
	 * getHeight >= 0
	 */
}
