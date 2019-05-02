package decorator.entities;

import model.services.IItem;

public class AbstractItemDecorator extends AbstractEntityDecorator implements IItem
{
	public AbstractItemDecorator(IItem d) {
		super(d);
	}

}
