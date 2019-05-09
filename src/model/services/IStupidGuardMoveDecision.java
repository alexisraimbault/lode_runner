package model.services;

public interface IStupidGuardMoveDecision extends ITestingShortestPathDecision<IGuard, MoveType>
{
	IPlayer getTarget();
	
	IStupidGuardMover getApplier();
	
	IAStarCalculator<IGuard, MoveType> getCalculator();
	
	IRandomGuardMoveDecision getAlternativeDecision();
	
	IGuardMoveAccepter getAccepter();
}
