package decorator.entities;

import model.gamestate.entities.GuardSummoner;
import model.services.IGuard;
import model.services.IGuardSummoner;

public class GuardSummonerDecorator extends AbstractSummonerDecorator<IGuard> implements IGuardSummoner
{

	public GuardSummonerDecorator(GuardSummoner d) {
		super(d);
	}

}
