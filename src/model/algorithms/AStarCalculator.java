package model.algorithms;

import model.services.ICharacter;
import model.services.ICharacterMoveAccepter;
import model.services.IEnvironment;
import model.services.IShortestPathsCalculator;

public class AStarCalculator implements IShortestPathsCalculator
{
	@Override
	public int[][] getPaths(ICharacter character, ICharacterMoveAccepter policy)
	{
		IEnvironment environment = character.getEnvironment();
		
		// TODO Algorithm A*
		
		return null;
	}

}
