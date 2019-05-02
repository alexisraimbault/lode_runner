package model.services;

public interface IScreen
{
	public int getWidth();
	public int getHeight();
	
	/*
	 * pre:
	 * 	0 <= x <= getWidth() - 1
	 * 	0 <= y <= getHeight() - 1
	 */
	public Nature getCellNature(int x, int y);
	
	/*
	 * pre:
	 * 	getCellNature(x, y) == Nature.PLATFORM
	 */
	public void dig(int x, int y);
	
	/*
	 * pre:
	 * 	getCellNature(x, y) == Nature.HOLE
	 */
	public void fill(int x, int y);
	
	/*
	 * invariants:
	 * getWidth() >= 0
	 * getHeight >= 0
	 * getWidth() const
	 * getHeight() const
	 */
}
