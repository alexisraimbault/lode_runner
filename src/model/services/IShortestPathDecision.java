package model.services;

public interface IShortestPathDecision<Character extends ICharacter, CommandType extends Enum<CommandType>> extends IDecision<Character, CommandType>
{
	public IShortestPathCalculator<Character, CommandType> getCalculator();
	public ICell getTarget();
	
	/*
	 * post:
	 * 	getCommand(source) = getCalculator().getPath(source, getTarget(), getAccepter()).get(0)
	 */
	public CommandType getCommand(Character character); // inherited
}
