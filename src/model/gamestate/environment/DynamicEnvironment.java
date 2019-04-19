package model.gamestate.environment;

import model.services.IContent;
import model.services.IDynamicEnvironment;
import model.services.IDynamicScreen;
import model.services.Nature;

public class DynamicEnvironment implements IDynamicEnvironment
{
	private IContent[][] contents;
	private IDynamicScreen screen;
	
	public DynamicEnvironment(IDynamicScreen screen)
	{
		this.screen = screen;
	}
	
	@Override
	public void setCellNature(int x, int y, Nature nature)
	{
		contents[x][y].clear();
		screen.setCellNature(x, y, nature);
	}
	
	@Override
	public void resize(int width, int height)
	{
		IContent[][] contents = new IContent[width][height];
		
		for(int x = 0; x < width; ++x)
		{
			for(int y = 0; y < height; ++y)
			{
				contents[x][y] = new Content();
			}
		}
		
		for(int x = 0; x < getWidth(); ++x)
		{
			for(int y = 0; y < getHeight(); ++y)
			{
				contents[x][y] = this.contents[x][y];
			}
		}

		this.contents = contents;
		
		screen.resize(width, height);
	}

	@Override
	public IContent getCellContent(int x, int y)
	{
		return contents[x][y];
	}

	@Override
	public Nature getCellNature(int x, int y)
	{
		return screen.getCellNature(x, y);
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
}
