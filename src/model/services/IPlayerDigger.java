package model.services;

public interface IPlayerDigger extends ICommandApplier<IPlayer, DigType>
{
	ICommandAccepter<IPlayer, DigType> getAccepter();
}
