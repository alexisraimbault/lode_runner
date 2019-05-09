package contract.algorithms;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.algorithms.PlentyAndGuardsTesterDecorator;
import model.services.EntityType;
import model.services.ICell;
import model.services.IPlentyAndGuardsTester;
import model.services.Nature;

public class PlentyAndGuardsTesterContract extends PlentyAndGuardsTesterDecorator{

	public PlentyAndGuardsTesterContract(IPlentyAndGuardsTester d) {
		super(d);
		checkInvariant();
	}
	
	/**
	 * pre : cell.hasContent()
	 * post : @result = cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL || cell.getContent().contains(EntityType.GUARD)
	 */
	public boolean test(ICell cell){
		if(cell.getContent() == null)
			throw new PreconditionError("cell has no content set");
		checkInvariant();
		boolean res = super.test(cell);
		checkInvariant();
		if(!(res == ( cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL || cell.getContent().contains(EntityType.GUARD))))
			throw new PostconditionError(" res should be equal to cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL || cell.getContent().contains(EntityType.GUARD)");
		return res;
	}
	
	public void checkInvariant(){
		
	}

}
