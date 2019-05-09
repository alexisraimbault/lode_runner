package model.services;

public interface ITestingShortestPathDecision
	<Character extends ICharacter,
	CommandType extends Enum<CommandType>>
		extends IDecision<Character, CommandType>
{
	
	ICommandApplier<Character, CommandType> getApplier();
	
	IShortestPathCalculator<Character, CommandType> getCalculator();
	
	IDecision<Character, CommandType> getAlternativeDecision();
	
	/*
	 * set:
	 * 	path := getCalculator().getPath(source, getTarget(), getApplier())
	 * 	accepted := getAccepter().accept(character)
	 * 	pathExist := !(path == null || path.isEmpty());
	 * post:
	 * 	pathExist =>
	 * 		accepted.contains(path.get(0)) =>
	 * 			@result == path.get(0)
	 * 		!accepted.contains(path.get(0)) =>
	 * 			@result == getAlternativeDecision().getCommand(character)
	 * 	!pathExist =>
	 * 		@result == getAlternativeDecision().getCommand(character)
	 */
	CommandType getCommand(Character entity);
	

	ICell getTarget();
	
	/*
	 * invariants:
	 * 	getAccepter() == getAlternativeDecision().getAccepter()
	 */
}
