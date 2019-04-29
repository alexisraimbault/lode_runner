package model.services;

import java.util.List;

public interface ISummonerPool
{
	IPlayerSummoner getPlayerSummoner();
	List<IGuardSummoner> getGuardSummoners();
	List<ITreasureSummoner> getTreasureSummoners();
	List<ICoinSummoner> getCoinSummoners();
	
	IPlayer getPlayer();
	List<IGuard> getGuards();
	List<ITreasure> getTreasures();
	void clearItems();
}
