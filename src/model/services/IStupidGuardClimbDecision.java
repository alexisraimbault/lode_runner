package model.services;

public interface IStupidGuardClimbDecision 
	extends IDecision<IGuard, ClimbType>
{
	IGuardClimbAccepter getAccepter();
	
	IPlayerSummoner getPlayerSummoner();
	
	IAStarCalculator<IGuard, MoveType> getCalculator();
}
