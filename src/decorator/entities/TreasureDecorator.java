package decorator.entities;

import model.services.ITreasure;

public class TreasureDecorator extends AbstractEntityDecorator implements ITreasure
{

	public TreasureDecorator(ITreasure d) {
		super(d);
	}

}
