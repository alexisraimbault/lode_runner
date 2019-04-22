package model.services;

import java.util.List;
import java.util.Set;

public interface IShortestPathCalculator
{
	List<MoveType> getPath(ICharacter character, ICharacter target, ICharacterMoveAccepter accepter);
}
