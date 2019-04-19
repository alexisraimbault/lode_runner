package model.gamestate.environment;

import model.services.IContent;
import model.services.IDynamicEnvironment;
import model.services.IEnvironment;
import model.services.Nature;

public class Environment implements IEnvironment
{
	private IDynamicEnvironment environment;
	
	public Environment(IDynamicEnvironment environment)
	{
		this.environment = environment;
	}

	@Override
	public void dig(int x, int y)
	{
		environment.setCellNature(x, y, Nature.HOLE);
	}

	@Override
	public void fill(int x, int y)
	{
		environment.setCellNature(x, y, Nature.PLATFORM);
	}

	@Override
	public Nature getCellNature(int x, int y)
	{
		return environment.getCellNature(x, y);
	}

	@Override
	public int getWidth()
	{
		return environment.getWidth();
	}

	@Override
	public int getHeight()
	{
		return environment.getHeight();
	}

	@Override
	public IContent getCellContent(int x, int y)
	{
		return environment.getCellContent(x, y);
	}
}
