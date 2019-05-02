package contract.entities;

import decorator.entities.AbstractEntityDecorator;
import model.gamestate.entities.AbstractEntity;
import model.services.IEntity;

public class AbstractEntityContract extends AbstractEntityDecorator{

	public AbstractEntityContract(IEntity d) {
		super(d);
	}
	
	public void checkInvariant() {
		
	}
	
}
