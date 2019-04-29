package model.algorithms;

import model.services.ICommandAccepter;
import model.services.IMover;
import model.services.IPlayer;
import model.services.IPlayerCommandAccepter;
import model.services.IPlayerCommandApplier;
import model.services.IPlayerDigger;
import model.services.PlayerCommandType;

public class PlayerCommandApplier implements IPlayerCommandApplier
{
	private IMover<IPlayer> mover;
	private IPlayerDigger digger;
	private IPlayerCommandAccepter accepter;
	
	public PlayerCommandApplier(IMover<IPlayer> mover, IPlayerDigger digger, IPlayerCommandAccepter accepter)
	{
		this.mover = mover;
		this.digger = digger;
		this.accepter = accepter;
	}
	
	public PlayerCommandApplier()
	{
		this(new Mover<>(new PlayerMoveAccepter()), new PlayerDigger(), new PlayerCommandAccepter());
	}

	@Override
	public ICommandAccepter<IPlayer, PlayerCommandType> getAccepter()
	{
		return accepter;
	}

	@Override
	public void apply(PlayerCommandType type, IPlayer player)
	{
		if(type.isMoveType())
			mover.apply(type.moveType(), player);
		else if(type.isDigType())
			digger.apply(type.digType(), player);
	}

}