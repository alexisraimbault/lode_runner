package model.algorithms;

import model.services.ICell;
import model.services.IPlentyAndHoleTester;
import model.services.IPlentyTester;
import model.services.Nature;

public class PlentyAndHoleTester implements IPlentyAndHoleTester
{
	IPlentyTester plenty_tester = new PlentyTester();
	
	@Override
	public boolean test(ICell cell)
	{
		Nature nature = cell.getNature();
		return nature == Nature.HOLE || plenty_tester.test(cell);
	}

	@Override
	public IPlentyTester getPlenty_tester() {
		return plenty_tester;
	}

}
