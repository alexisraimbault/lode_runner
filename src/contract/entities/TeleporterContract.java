package contract.entities;

import decorator.entities.TeleporterDecorator;
import model.services.ITeleporter;

public class TeleporterContract extends TeleporterDecorator{

	public TeleporterContract(ITeleporter d) {
		super(d);
		checkInvariant();
	}

	public void checkInvariant() {
		
	}

}
