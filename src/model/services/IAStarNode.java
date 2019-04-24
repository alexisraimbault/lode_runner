package model.services;

import java.util.List;

public interface IAStarNode
{
	public ICell getCell();
	public int getCost();
	public int getHeuristic();
	public int getWeight();
	public boolean hasPred();
	public IAStarNode getPred();
	public MoveType getMoveType();
	public List<MoveType> getPath();
}
