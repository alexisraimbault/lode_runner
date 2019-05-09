package model.services;

import java.util.function.Predicate;

public interface IPlentyAndHoleTester extends Predicate<ICell>
{

	/**
	 * pre : cell.hasContent()
	 * post : @result = cell.getNature() == Nature.HOLE || plenty_tester.test(cell)
	 * 
	 * 
	 */
	public boolean test(ICell cell);
	
	IPlentyTester getPlenty_tester();
}
