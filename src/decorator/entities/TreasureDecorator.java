package decorator.entities;

import model.gamestate.entities.Treasure;
import model.services.ITreasure;

public class TreasureDecorator extends AbstractEntityDecorator implements ITreasure
{

	public TreasureDecorator(Treasure d) {
		super(d);
	}

}
