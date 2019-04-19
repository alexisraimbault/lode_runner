package model.services;

import java.util.List;

public interface IEntityPool
{
	IPlayer getPlayer();
	List<IGuard> getGuards();
	List<ITreasure> getTreasures();
}
