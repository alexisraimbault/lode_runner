package model.algorithms;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Predicate;

import model.gamestate.entities.Cell;
import model.services.ClimbType;
import model.services.ICell;
import model.services.ICharacter;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.IGuard;
import model.services.IGuardClimbAccepter;
import model.services.IPlentyTester;
import model.services.MoveType;
import model.services.Nature;

public class GuardClimbAccepter extends GuardClimbAccepterBase implements IGuardClimbAccepter
{

	public GuardClimbAccepter(IPlentyTester plenty_tester)
	{
		super(plenty_tester);
	}
	
	public GuardClimbAccepter()
	{
		this(new PlentyTester());
	}

	@Override
	public IPlentyTester getTester() {
		return (IPlentyTester) super.getPlentyTester();
	}
	
}
