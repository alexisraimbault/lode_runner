package contract.entities;

import contract.contracterr.InvariantError;
import decorator.entities.CellDecorator;
import model.services.ICell;

public class CellContract extends CellDecorator{

	public CellContract(ICell d) {
		super(d);
		checkInvariant();
	}
	
	public void checkInvariant() {
		if(!(0 <= getX() && getX() <= getEnvironment().getWidth() - 1))
			throw new InvariantError("getX() must be between 0 and Environment.Width - 1");
		if(!(0 <= getY() &&  getY() <= getEnvironment().getHeight() - 1))
			throw new InvariantError("getY() must be between 0 and Environment.Height - 1");
		if(!(getNature() == getEnvironment().getCellNature(getX(), getY())))
				throw new InvariantError("getNature() must be the same as the environment nature for getX() and getY()");
		if(!(getContent() == getEnvironment().getCellContent(getX(), getY())))
				throw new InvariantError("getContent() must be the same as the environment nature for getX() and getY()");
	}

}
