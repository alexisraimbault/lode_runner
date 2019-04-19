package model.services;

public interface IShortestPathsCalculator
{
	int[][] getPaths(ICharacter character, ICharacterMovingPolicy policy);
}
