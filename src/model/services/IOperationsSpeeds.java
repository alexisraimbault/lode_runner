package model.services;

public interface IOperationsSpeeds
{
	long get(PlayerCommandType type);
	long get(GuardCommandType type);
	long hole();
}
