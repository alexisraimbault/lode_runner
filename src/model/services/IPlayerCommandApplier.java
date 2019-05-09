package model.services;

public interface IPlayerCommandApplier extends ICommandApplier<IPlayer, PlayerCommandType>
{
	IPlayerCommandAccepter getAccepter();
}
