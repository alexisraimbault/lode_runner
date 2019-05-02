package contract.entities;

import decorator.entities.GuardSummonerDecorator;
import model.services.IGuardSummoner;

public class GuardSummonerContract extends GuardSummonerDecorator{

	public GuardSummonerContract(IGuardSummoner d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void checkInvariant() {
		
	}

}
