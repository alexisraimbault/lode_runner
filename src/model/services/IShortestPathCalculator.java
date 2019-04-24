package model.services;

import java.util.List;

public interface IShortestPathCalculator<Character extends ICharacter>
{
	public List<MoveType> getPath(Character entity, ICell target, IMover<Character> accepter);
}
