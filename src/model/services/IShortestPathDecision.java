package model.services;

public interface IShortestPathDecision<Character extends ICharacter, CommandType extends Enum<CommandType>> extends IDecision<Character, CommandType>
{

	public ICommandApplier<Character, CommandType> getApplier();
	public IShortestPathCalculator<Character, CommandType> getCalculator();
	
	/*
	 * post set:
	 * 	path := getCalculator().getPath(source, getTarget(), getAccepter())
	 * post:
	 * 	!(path == null || path.isEmpty()) => @result = path.get(0)
	 */
	CommandType getCommand(Character character);
	
	/*
	 * invariants:
	 * 	getApplier().getAccepter() == getAccepter()
	 */
}
