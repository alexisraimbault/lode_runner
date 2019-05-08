package contract.algorithms;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.PlentyAndCharacterTesterDecorator;
import model.services.ICell;
import model.services.IPlentyAndCharacterTester;

public class PlentyAndCharacterTesterContract extends PlentyAndCharacterTesterDecorator{

	public PlentyAndCharacterTesterContract(IPlentyAndCharacterTester d) {
		super(d);
	}

	/**
	 * pre : cell.hasContent()
	 * post: @result = cell.getContent().nbCharacters() > 0 || plenty_tester.test(cell)
	 * 
	 */
	public boolean test(ICell cell){
		if(cell.getContent() == null)
			throw new PreconditionError("cell has no content set");
		boolean res = super.test(cell);
		if(!(res == (cell.getContent().nbCharacters() > 0 || getPlenty_tester().test(cell))))
			throw new PostconditionError(" res should be equal to cell.getContent().nbCharacters() > 0 || getPlenty_tester().test(cell))");
		return res;
	}
	
	public void checkInvariant(){
		
	}
}
