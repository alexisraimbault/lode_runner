package decorator.entities;

import model.services.IGuard;
import model.services.IGuardSummoner;

public class GuardSummonerDecorator extends AbstractSummonerDecorator<IGuard> implements IGuardSummoner
{

	public GuardSummonerDecorator(IGuardSummoner d) {
		super(d);
	}

}
