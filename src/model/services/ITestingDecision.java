package model.services;

public interface ITestingDecision
	<Entity extends IEntity,
	CommandType extends Enum<CommandType>>
		extends IDecision<Entity, CommandType>
{

	IDecision<Entity, CommandType> getTestingDecision();
	
	IDecision<Entity, CommandType> getAlternativeDecision();
	
	/*
	 * set:
	 * 	path := getCalculator().getPath(source, getTarget(), getAccepter())
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
	CommandType getCommand(Entity entity);
	
	/*
	 * invariants:
	 * 	getAccepter() == getAlternativeDecision().getAccepter()
	 */
}
