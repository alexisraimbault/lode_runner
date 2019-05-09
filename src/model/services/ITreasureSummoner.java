package model.services;

public interface ITreasureSummoner extends ISummoner<ITreasure>
{
	/*
	 * invariants:
	 * 	getInstance().getType() == EntityType.TREASURE
	 */
}
