package model.algorithms;

import model.services.IPlentyAndHoleTester;
import model.services.IStupidGuardClimbAccepter;

public class StupidGuardClimbAccepter extends GuardClimbAccepterBase implements IStupidGuardClimbAccepter
{

	public StupidGuardClimbAccepter(IPlentyAndHoleTester plenty_tester)
	{
		super(plenty_tester);
	}
	
	public StupidGuardClimbAccepter()
	{
		super(new PlentyAndHoleTester());
	}

}
