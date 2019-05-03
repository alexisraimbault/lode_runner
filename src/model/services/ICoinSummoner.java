package model.services;

public interface ICoinSummoner extends ISummoner<ICoin>
{
	/*
	 * invariants:
	 * 	getInstance().getType() == EntityType.COIN
	 */
}
