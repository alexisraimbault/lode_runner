package decorator.entities;

import model.services.IFantom;
import model.services.MoveType;

public class FantomDecorator extends AbstractOperatingEntityDecorator<MoveType> implements IFantom
{

	public FantomDecorator(IFantom d) {
		super(d);
	}
	
}
