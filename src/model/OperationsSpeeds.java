package model;

import java.util.EnumMap;
import java.util.Map;

import model.services.GuardCommandType;
import model.services.IOperationsSpeeds;
import model.services.PlayerCommandType;

public class OperationsSpeeds implements IOperationsSpeeds
{
	private Map<PlayerCommandType, Integer> pspeeds;
	private Map<GuardCommandType, Integer> gspeeds;
	private int hspeed;
	
	public OperationsSpeeds(Map<PlayerCommandType, Integer> pspeeds, Map<GuardCommandType, Integer> gspeeds, int hspeed)
	{
		this.pspeeds = pspeeds;
		this.gspeeds = gspeeds;
		this.hspeed = hspeed;
	}
	
	@Override
	public int get(PlayerCommandType type)
	{
		return pspeeds.get(type);
	}

	@Override
	public int get(GuardCommandType type)
	{
		return gspeeds.get(type);
	}

	@Override
	public int hole()
	{
		return hspeed;
	}
	
	public static OperationsSpeeds default_speeds = null;
	
	static
	{
		Map<PlayerCommandType, Integer> pspeeds = new EnumMap<>(PlayerCommandType.class);
		Map<GuardCommandType, Integer> gspeeds = new EnumMap<>(GuardCommandType.class);
		
		int player_move_speed = 1;
		int player_dig_speed = 1;
		int guard_move_speed = player_move_speed * 2;
		int guard_climb_speed = guard_move_speed * 2;
		int hole_speed = guard_move_speed * 6;
		
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
