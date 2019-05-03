package model.services;

public interface ITestingStupidGuardDecision extends ITestingDecision<IGuard, GuardCommandType>
{
	IStupidGuardDecision getTestingDecision();
	
	IRandomGuardDecision getAlternativeDecision();
}
