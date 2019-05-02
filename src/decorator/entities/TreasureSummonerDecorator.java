package decorator.entities;

import model.services.ITreasure;
import model.services.ITreasureSummoner;

public class TreasureSummonerDecorator  extends AbstractSummonerDecorator<ITreasure> implements ITreasureSummoner
{

	public TreasureSummonerDecorator(ITreasureSummoner d) {
		super(d);
	}

}
