package model.gamestate.environment;

import model.services.EntityType;
import model.services.IContent;
import model.services.IDynamicEnvironment;
import model.services.IDynamicScreen;
import model.services.IEditableEnvironment;
import model.services.IEditableScreen;
import model.services.Nature;

public class EditableEnvironment implements IEditableEnvironment
{
	private IDynamicEnvironment environment;
	private IEditableScreen screen;
	
	public EditableEnvironment(IDynamicScreen screen)
	{
		this.environment = new DynamicEnvironment(screen);
		this.screen = new EditableScreen(screen);
	}
	
	@Override
	public boolean isPlayable()
	{
		if(!screen.isPlayable())
			return false;
		
		int count_players = 0;
		int count_treasures = 0;
		for(int x = 0; x < getWidth(); ++x)
		{
			for(int y = 0; y < getHeight(); ++y)
			{
				Nature nature = getCellNature(x, y);
				IContent content = getCellContent(x, y);
				
				if(content.nbCharacters() > 1)
					return false;
				
				if(nature.isPlenty() && !content.isEmpty())
					return false;
				
				for(EntityType type : EntityType.values())
				{
					switch(type)
					{
					case PLAYER:
						count_players += content.counts(type);
						if(getCellNature(x, y) != Nature.EMPTY)
							return false;
						break;
					case GUARD:
						break;
					case TREASURE:
						count_treasures += content.counts(type);
						if(y == 0)
							return false;
						Nature down_nature = getCellNature(x, y - 1);
						if(!down_nature.isPlenty())
							return false;
						break;
					default:
						break;
					}
				}
			}
		}
		// + check player can access all treasures
		return count_players == 1 && count_treasures > 0;
	}
	
	@Override
	public void setCellNature(int x, int y, Nature nature)
	{
		screen.setCellNature(x, y, nature);
	}

	@Override
	public IDynamicEnvironment produce()
	{
		return environment;
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
	public void resize(int width, int height)
	{
		environment.resize(width, height);
	}

	@Override
	public Nature getCellNature(int x, int y)
	{
		return screen.getCellNature(x, y);
	}

	@Override
	public IContent getCellContent(int x, int y)
	{
		return environment.getCellContent(x, y);
	}

}
