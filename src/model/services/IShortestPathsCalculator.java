package model.services;

public interface IShortestPathsCalculator
{
	int[][] getPaths(ICharacter character, ICharacterMoveAccepter policy);
}
