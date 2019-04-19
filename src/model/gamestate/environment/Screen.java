package model.gamestate.environment;

import model.services.IDynamicScreen;
import model.services.IScreen;
import model.services.Nature;

public class Screen implements IScreen
{
	private IDynamicScreen screen;
	
	public Screen(IDynamicScreen screen)
	{
		this.screen = screen;
	}
	
	@Override
	public int getWidth()
	{
		return screen.getWidth();
	}

	@Override
	public int getHeight()
	{
		return screen.getHeight();
	}

	@Override
	public Nature getCellNature(int x, int y)
	{
		return screen.getCellNature(x, y);
	}

	@Override
	public void dig(int x, int y)
	{
		screen.setCellNature(x, y, Nature.HOLE);
	}

	@Override
	public void fill(int x, int y)
	{
		screen.setCellNature(x, y, Nature.PLATFORM);
	}
}
