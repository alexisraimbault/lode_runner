package model.services;

public interface IExecutedCharacterOperation<CommandType extends Enum<CommandType>> extends IExecutedOperation
{
	public CommandType getOperationType();
}
