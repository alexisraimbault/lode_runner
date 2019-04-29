package model.services;

public interface IAStarDecision
	<Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IShortestPathDecision<Character, CommandType>
{
	public boolean hasTarget();
	
	/*
	 * pre:
	 * 	hasTarget()
	 */
	public void setTarget(ICell target);
	
	/*
	 * invariants:
	 * 	getCalculator() instanceof IAStarCalculator<Character, CommandType>
	 */
}
