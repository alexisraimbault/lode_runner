package model.services;

import java.util.Set;

public interface IShortestPathsCalculator
{
	Set<MoveType> getPaths(ICharacter character, ICharacterMoveAccepter accepter);
}
