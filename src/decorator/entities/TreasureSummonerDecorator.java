package decorator.entities;

import model.gamestate.entities.TreasureSummoner;
import model.services.ITreasure;
import model.services.ITreasureSummoner;

public class TreasureSummonerDecorator  extends AbstractSummonerDecorator<ITreasure> implements ITreasureSummoner
{

	public TreasureSummonerDecorator(TreasureSummoner d) {
		super(d);
	}

}
