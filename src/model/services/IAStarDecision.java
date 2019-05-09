package model.services;

public interface IAStarDecision
	<Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IShortestPathDecision<Character, CommandType>
{
	/*
	 * init:
	 * 	!hasTarget()
	 */
	
	IDecision<Character, CommandType> getAlternativeDecision();
	
	public boolean hasTarget();
	
	/*
	 * post:
	 * 	getTarget() == target
	 */
	public void setTarget(ICell target);
	
	/*
	 * pre:
	 * 	!getAlternativeDecision().accept(character).isEmpty()
	 * 
	 * set:
	 * 	path := getCalculator().getPath(source, getTarget(), getAccepter())
	 * 	accepted := getAlternativeDecision().getAccepter().accept(character)
	 * post:
	 * 	!(path == null || path.isEmpty()) =>
	 * 		getAlternativeDecision().getAccepter(path.get(0)).contains(path.get(0)) =>
	 * 			@result == path.get(0)
	 * 		@result == getAlternativeDecision().getCommand(character)
	 * 	(path == null || path.isEmpty()) => @result == getAlternativeDecision().getCommand(character)
	 */
	//CommandType getCommand(Character character)
}
