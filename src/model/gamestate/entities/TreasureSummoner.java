package model.gamestate.entities;

import model.services.ITreasure;
import model.services.ITreasureSummoner;

public class TreasureSummoner extends AbstractSummoner<ITreasure> implements ITreasureSummoner
{

	public TreasureSummoner(ITreasure instance)
	{
		super(instance);
	}
	

}
