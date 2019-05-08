package model.services;

import java.util.function.Predicate;

public interface IPlentyAndCharacterTester extends Predicate<ICell>
{
	/**
	 * pre : cell.hasContent()
	 * post: @result = cell.getContent().nbCharacters() > 0 || plenty_tester.test(cell)
	 * 
	 */
	public boolean test(ICell cell);
	
	IPlentyTester getPlenty_tester();

}
