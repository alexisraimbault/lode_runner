package contract.entities;

import decorator.entities.PlayerSummonerDecorator;
import model.services.IPlayerSummoner;

public class PlayerSummonerContract extends PlayerSummonerDecorator{

	public PlayerSummonerContract(IPlayerSummoner d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

}
