package decorator.entities;

import model.gamestate.entities.AbstractItem;
import model.services.IItem;

public class AbstractItemDecorator extends AbstractEntityDecorator implements IItem
{
	public AbstractItemDecorator(AbstractItem d) {
		super(d);
	}

}
