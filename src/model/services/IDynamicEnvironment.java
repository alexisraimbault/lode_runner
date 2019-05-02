package model.services;

public interface IDynamicEnvironment extends IDynamicScreen
{
	/*
	 * pre:
	 * 	0 <= x <= getWidth() - 1
	 * 	0 <= y <= getHeight() - 1
	 */
	public IContent getCellContent(int x, int y);
}
