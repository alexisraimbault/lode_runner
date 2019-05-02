package contract.entities;

import decorator.entities.CellDecorator;
import model.services.ICell;

public class CellContract extends CellDecorator{

	public CellContract(ICell d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void checkInvariant() {
		
	}

}
