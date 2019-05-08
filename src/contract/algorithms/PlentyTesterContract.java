package contract.algorithms;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.PlentyTesterDecorator;
import model.services.ICell;
import model.services.IPlentyTester;
import model.services.Nature;

public class PlentyTesterContract extends PlentyTesterDecorator{

	public PlentyTesterContract(IPlentyTester d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * post : @result = (cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL)
	 */
	public boolean test(ICell cell){
		if(cell.getContent() == null)
			throw new PreconditionError("cell has no content set");
		boolean res = super.test(cell);
		if(!(res == (cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL)))
			throw new PostconditionError(" res should be equal to cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL");
		return res;
	}
	
	public void checkInvariant(){
		
	}

}
