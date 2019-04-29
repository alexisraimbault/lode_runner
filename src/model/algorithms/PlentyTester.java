package model.algorithms;

import model.services.ICell;
import model.services.IPlentyTester;
import model.services.Nature;

public class PlentyTester implements IPlentyTester
{

	@Override
	public boolean test(ICell cell)
	{
		Nature nature = cell.getNature();
		return nature == Nature.PLATFORM || nature == Nature.METAL;
	}

}
