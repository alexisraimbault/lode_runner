package model.services;

public interface IExecutedOperation<CommandType extends Enum<CommandType>> extends IOperation<CommandType>
{
	public long getElapsedTime();
	public void update(long elapsed);
	public boolean isEnded();
}
