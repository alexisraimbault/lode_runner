package model.services;

public interface IEnvironment extends IScreen
{
	/*
	 * pre:
	 * 	0 <= x <= getWidth() - 1
	 * 	0 <= y <= getHeight() - 1
	 */
	public IContent getCellContent(int x, int y);
}
