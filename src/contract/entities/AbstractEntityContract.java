package contract.entities;

import contract.contracterr.InvariantError;
import decorator.entities.AbstractEntityDecorator;
import model.gamestate.entities.AbstractEntity;
import model.services.IEntity;

public class AbstractEntityContract extends AbstractEntityDecorator{

	public AbstractEntityContract(IEntity d) {
		super(d);
	}
	
	public void checkInvariant() {
		if(!getContent().contains(getType()))
			throw new InvariantError("the environment doesn't contain the entity");
	}
	
}
