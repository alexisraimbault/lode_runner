package model.algorithms;

import model.services.EntityType;
import model.services.ICell;
import model.services.IContent;
import model.services.IPlentyAndGuardsTester;
import model.services.Nature;

public class PlentyAndGuardsTester implements IPlentyAndGuardsTester
{

	@Override
	public boolean test(ICell cell)
	{
		Nature nature = cell.getNature();
		IContent content = cell.getContent();
		return nature == Nature.PLATFORM || nature == Nature.METAL || content.contains(EntityType.GUARD);
	}

}
