package decorator.entities;

import model.gamestate.entities.Fantom;
import model.services.IFantom;
import model.services.MoveType;

public class FantomDecorator extends AbstractOperatingEntityDecorator<MoveType> implements IFantom
{

	public FantomDecorator(Fantom d) {
		super(d);
	}
	
}
