package contract.entities;

import decorator.entities.TreasureSummonerDecorator;
import model.services.ITreasureSummoner;

public class TreasureSummonerContract extends TreasureSummonerDecorator{

	public TreasureSummonerContract(ITreasureSummoner d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void checkInvariant() {
		
	}

}
