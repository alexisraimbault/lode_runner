package model.algorithms;

import model.services.ICharacter;
import model.services.ICharacterMovingPolicy;
import model.services.IEnvironment;
import model.services.IShortestPathsCalculator;

public class ShortestPathsCalculator implements IShortestPathsCalculator
{

	@Override
	public int[][] getPaths(ICharacter character, ICharacterMovingPolicy policy)
	{
		IEnvironment environment = character.getEnvironment();
		int paths[][] = new int[environment.getWidth()][environment.getHeight()];
		
		// TODO Algorithm A*
		
		return paths;
	}

}
