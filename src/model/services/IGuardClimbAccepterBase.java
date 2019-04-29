package model.services;

import java.util.function.Predicate;

public interface IGuardClimbAccepterBase extends ICommandAccepter<IGuard, ClimbType>
{
	public Predicate<ICell> getPlentyTester();
}
