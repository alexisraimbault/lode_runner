package contract.entities;

import decorator.entities.TreasureDecorator;
import model.services.ITreasure;

public class TreasureContract extends TreasureDecorator{

	public TreasureContract(ITreasure d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void checkInvariant() {
		
	}

}
