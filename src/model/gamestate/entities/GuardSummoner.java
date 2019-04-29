package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IContent;
import model.services.IGuardSummoner;
import model.services.IGuard;
import model.services.MoveType;
import model.services.Nature;

public class GuardSummoner extends AbstractSummoner<IGuard> implements IGuardSummoner
{

	public GuardSummoner(IGuard instance)
	{
		super(instance);
	}

	@Override
	public void destroy()
	{
		IGuard instance = getInstance();
		if(Cell.hasNext(instance, MoveType.UP))
		{
			ICell up_cell = Cell.getNext(instance, MoveType.UP);
			Nature up_nature = up_cell.getNature();
			IContent up_content = up_cell.getContent();
			
			if(up_nature == Nature.EMPTY)
			{
				up_content.add(EntityType.COIN);
			}
		}
		super.destroy();
	}
	
}
