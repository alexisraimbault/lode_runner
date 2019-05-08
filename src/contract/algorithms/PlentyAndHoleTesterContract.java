package contract.algorithms;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.PlentyAndHoleTesterDecorator;
import model.services.EntityType;
import model.services.ICell;
import model.services.IPlentyAndHoleTester;
import model.services.Nature;

public class PlentyAndHoleTesterContract extends PlentyAndHoleTesterDecorator{

	public PlentyAndHoleTesterContract(IPlentyAndHoleTester d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * pre : cell.hasContent()
	 * post : @result = cell.getNature() == Nature.HOLE || plenty_tester.test(cell)
	 * 
	 * 
	 */
	public boolean test(ICell cell){
		if(cell.getContent() == null)
			throw new PreconditionError("cell has no content set");
		boolean res = super.test(cell);
		if(!(res == ( cell.getNature() == Nature.HOLE || getPlenty_tester().test(cell))))
			throw new PostconditionError(" res should be equal to cell.getNature() == Nature.HOLE || getPlenty_tester().test(cell) || getPlenty_tester().test(cell))");
		return res;
	}
	
	public void checkInvariant(){
		
	}
}
