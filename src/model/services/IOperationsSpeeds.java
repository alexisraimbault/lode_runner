package model.services;

public interface IOperationsSpeeds
{
	public long get(PlayerCommandType type);
	public long get(GuardCommandType type);
	public long getHoleSpeed();
}
