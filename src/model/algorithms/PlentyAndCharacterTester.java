package model.algorithms;

import model.services.ICell;
import model.services.IContent;
import model.services.IPlentyAndCharacterTester;
import model.services.IPlentyTester;
import model.services.Nature;

public class PlentyAndCharacterTester implements IPlentyAndCharacterTester
{
	
	IPlentyTester plenty_tester = new PlentyTester();
	
	@Override
	public boolean test(ICell cell)
	{
		IContent content = cell.getContent();
		return content.nbCharacters() > 0 || plenty_tester.test(cell);
	}
	
}
