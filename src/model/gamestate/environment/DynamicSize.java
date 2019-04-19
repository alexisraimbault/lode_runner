package model.gamestate.environment;

import model.services.IDynamicSize;

public class DynamicSize implements IDynamicSize
{
	private int width = 0;
	private int height = 0;

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public void resize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
}
