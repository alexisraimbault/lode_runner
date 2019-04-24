package model.services;

public interface IShortestPathDecision<Character extends ICharacter> extends IDecision<Character, MoveType>
{
	public IShortestPathCalculator<Character> getCalculator();
	public ICell getTarget();
	
	/*
	 * post:
	 * 	getCommand(source) = getCalculator().getPath(source, getTarget(), getAccepter()).get(0)
	 */
	public MoveType getCommand(Character character); // inherited
}
