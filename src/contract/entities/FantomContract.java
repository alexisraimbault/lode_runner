package contract.entities;

import decorator.entities.FantomDecorator;
import model.services.IFantom;

public class FantomContract extends FantomDecorator{

	public FantomContract(IFantom d) {
		super(d);
		checkInvariant();
	}

	public void checkInvariant() {
		
	}

}
