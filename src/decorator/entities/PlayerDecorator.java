package decorator.entities;

import model.gamestate.entities.Player;
import model.services.IPlayer;
import model.services.PlayerCommandType;

public class PlayerDecorator extends AbstractOperatingEntityDecorator<PlayerCommandType> implements IPlayer
{

	public PlayerDecorator(Player d) {
		super(d);
	}

}
