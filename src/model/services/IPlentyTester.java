package model.services;

import java.util.function.Predicate;

public interface IPlentyTester extends Predicate<ICell>
{

	/**
	 * 
	 * post : @result = (cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL)
	 */
	public boolean test(ICell cell);
	/*{
		Nature nature = cell.getNature();
		return nature == Nature.PLATFORM || nature == Nature.METAL;
	}*/
}