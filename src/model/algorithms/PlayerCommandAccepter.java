package model.algorithms;

import java.util.EnumSet;
import java.util.Set;

import model.services.DigType;
import model.services.IPlayer;
import model.services.IPlayerCommandAccepter;
import model.services.IPlayerDigAccepter;
import model.services.IPlayerMoveAccepter;
import model.services.MoveType;
import model.services.PlayerCommandType;

public class PlayerCommandAccepter implements IPlayerCommandAccepter
{
	private IPlayerMoveAccepter move_accepter;
	private IPlayerDigAccepter dig_accepter;
	
	public PlayerCommandAccepter(IPlayerMoveAccepter move_accepter, IPlayerDigAccepter dig_accepter)
	{
		this.move_accepter = move_accepter;
		this.dig_accepter = dig_accepter;
	}
	
	public PlayerCommandAccepter()
	{
		this(new PlayerMoveAccepter(new CharacterMoveAccepter()), new PlayerDigAccepter());
	}

	@Override
	public Set<PlayerCommandType> accept(IPlayer player)
	{
		Set<PlayerCommandType> accepted = EnumSet.noneOf(PlayerCommandType.class);
		Set<MoveType> moves = move_accepter.accept(player);
		Set<DigType> digs = dig_accepter.accept(player);
		
		for(MoveType type : moves)
			accepted.add(PlayerCommandType.get(type));
		for(DigType type : digs)
			accepted.add(PlayerCommandType.get(type));
		return accepted;
	}
}
