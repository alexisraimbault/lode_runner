package contract.entities;

import decorator.entities.AbstractItemDecorator;
import model.services.IItem;

public class AbstractItemContract extends AbstractItemDecorator {

	public AbstractItemContract(IItem d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void checkInvariant() {
		
	}

}
