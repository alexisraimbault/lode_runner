package model.services;

import java.util.Set;

public interface ICommandAccepter
	<Cell extends ICell,
	CommandType extends Enum<CommandType>>
{
	public boolean accept(CommandType type, Cell cell);
	
	/*
	 * post:
	 * 	forall command
	 * 		accept(command, entity) <=> accept(entity).contains(command)
	 */
	public Set<CommandType> accept(Cell cell);
}
