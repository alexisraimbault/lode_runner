package model.gamestate.operations;

import java.util.EnumMap;
import java.util.Map;

import model.services.GuardCommandType;
import model.services.IOperationsSpeeds;
import model.services.PlayerCommandType;

public class OperationsSpeeds implements IOperationsSpeeds
{
	private Map<PlayerCommandType, Long> pspeeds;
	private Map<GuardCommandType, Long> gspeeds;
	private long hspeed;
	
	public OperationsSpeeds(Map<PlayerCommandType, Long> pspeeds, Map<GuardCommandType, Long> gspeeds, long hspeed)
	{
		this.pspeeds = pspeeds;
		this.gspeeds = gspeeds;
		this.hspeed = hspeed;
	}
	
	@Override
	public long get(PlayerCommandType type)
	{
		return pspeeds.get(type);
	}

	@Override
	public long get(GuardCommandType type)
	{
		return gspeeds.get(type);
	}

	@Override
	public long hole()
	{
		return hspeed;
	}
	
	public static OperationsSpeeds default_speeds = null;
	
	static
	{
		Map<PlayerCommandType, Long> pspeeds = new EnumMap<>(PlayerCommandType.class);
		Map<GuardCommandType, Long> gspeeds = new EnumMap<>(GuardCommandType.class);
		
		long player_move_speed = 1000000000l;
		long player_dig_speed = 1000000000l;
		long guard_move_speed = player_move_speed * 2;
		long guard_climb_speed = guard_move_speed * 2;
		long hole_speed = guard_move_speed * 6;
		
		for(PlayerCommandType type : PlayerCommandType.values())
		{
			if(type.isMoveType())
				pspeeds.put(type, player_move_speed);
			if(type.isDigType())
				pspeeds.put(type, player_dig_speed);
		}
		
		for(GuardCommandType type : GuardCommandType.values())
		{
			if(type.isMoveType())
				gspeeds.put(type, guard_move_speed);
			if(type.isClimbType())
				gspeeds.put(type, guard_climb_speed);
		}
		
		default_speeds = new OperationsSpeeds(pspeeds, gspeeds, hole_speed);
	}

}
