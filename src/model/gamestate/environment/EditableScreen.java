package model.gamestate.environment;

import model.services.IDynamicScreen;
import model.services.IEditableScreen;
import model.services.Nature;

public class EditableScreen implements IEditableScreen
{
	private IDynamicScreen screen;
	private int count_holes;
	private int count_metals;
	
	public EditableScreen(IDynamicScreen screen)
	{
		this.screen = screen;
		this.count_holes = 0;
		this.count_metals = 0;
	}

	@Override
	public boolean isPlayable()
	{
		return count_holes == 0 && count_metals == getWidth();
	}
	
	@Override
	public void setCellNature(int x, int y, Nature nature)
	{
		if(getCellNature(x, y) == Nature.HOLE)
			--count_holes;
		else if(y == 0 && getCellNature(x, y) == Nature.METAL)
			--count_metals;
		
		screen.setCellNature(x, y, nature);
			
		if(getCellNature(x, y) == Nature.HOLE)
			++count_holes;
		else if(y == 0 && getCellNature(x, y) == Nature.METAL)
			++count_metals;
	}

	@Override
	public IDynamicScreen produce()
	{
		return screen;
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

	@Override
	public void resize(int width, int height)
	{
		screen.resize(width, height);
	}
}
