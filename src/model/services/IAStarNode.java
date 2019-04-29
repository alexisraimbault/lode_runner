package model.services;

import java.util.List;

public interface IAStarNode<CommandType extends Enum<CommandType>>
{
	public ICell getCell();
	public int getCost();
	public int getHeuristic();
	public int getWeight();
	public boolean hasPred();
	public IAStarNode<CommandType> getPred();
	public CommandType getCommandType();
	public List<CommandType> getPath();
}
