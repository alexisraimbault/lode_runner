package model.gamestate.environment;

import model.services.IDynamicScreen;
import model.services.Nature;

public class DynamicScreen extends DynamicSize implements IDynamicScreen
{
	private Nature[][] natures;
	
	public DynamicScreen()
	{
		this.natures = null;
	}

	@Override
	public Nature getCellNature(int x, int y)
	{
		return natures[x][y];
	}

	@Override
	public void setCellNature(int x, int y, Nature nature)
	{
		natures[x][y] = nature;
	}

	@Override
	public void resize(int width, int height)
	{
		Nature[][] natures = new Nature[width][height];
		
		for(int x = 0; x < width; ++x)
		{
			for(int y = 0; y < height; ++y)
			{
				natures[x][y] = Nature.EMPTY;
			}
		}
		
		for(int x = 0; x < getWidth(); ++x)
		{
			for(int y = 0; y < getHeight(); ++y)
			{
				natures[x][y] = this.natures[x][y];
			}
		}
		
		this.natures = natures;
		
		super.resize(width, height);
	}
	
	
}
