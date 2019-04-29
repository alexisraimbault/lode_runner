package model.services;

import java.util.List;

public interface IGameState
{
	public IEnvironment getEnvironment();
	public ISummonerPool getPool();
	public List<IHole> getHoles();
	public IOperationsSpeeds getSpeeds();
	public int getScoreToReach();
	public ICell getGuardRespawnCell();
}
