package model.services;

public interface IStupidGuardDecision extends IShortestPathDecision<IGuard, GuardCommandType>
{
	IAStarCalculator<IGuard, GuardCommandType> getCalculator();
	
	IStupidGuardCommandAccepter getAccepter();
}
