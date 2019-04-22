package model.services;

import java.util.List;
import java.util.Set;

public interface IShortestPathsCalculator
{
	List<MoveType> getPaths(ICharacter character, ICharacterMoveAccepter accepter);
}
