package contract.entities;

import contract.contracterr.InvariantError;
import decorator.entities.AbstractItemDecorator;
import model.services.IItem;

public class AbstractItemContract extends AbstractItemDecorator {

	public AbstractItemContract(IItem d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void checkInvariant() {
		if(!getType().isItem())
			throw new InvariantError("the type of the item isn't an item");
		
	}

}
