package contract.entities;

import decorator.entities.PlayerDecorator;
import model.services.IPlayer;

public class PlayerContract extends PlayerDecorator{

	public PlayerContract(IPlayer d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void checkInvariant() {
		
	}

}
