package decorator.entities;

import model.services.IPlayer;
import model.services.PlayerCommandType;

public class PlayerDecorator extends AbstractOperatingEntityDecorator<PlayerCommandType> implements IPlayer
{

	public PlayerDecorator(IPlayer d) {
		super(d);
	}

}
