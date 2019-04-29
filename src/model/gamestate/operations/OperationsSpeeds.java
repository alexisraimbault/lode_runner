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
	private long hole_speed;
	
	public OperationsSpeeds(Map<PlayerCommandType, Long> pspeeds, Map<GuardCommandType, Long> gspeeds, long hole_speed)
	{
		this.pspeeds = pspeeds;
		this.gspeeds = gspeeds;
		this.hole_speed = hole_speed;
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
	
	public static OperationsSpeeds default_speeds = null;
	
	static
	{
		Map<PlayerCommandType, Long> pspeeds = new EnumMap<>(PlayerCommandType.class);
		Map<GuardCommandType, Long> gspeeds = new EnumMap<>(GuardCommandType.class);
		
		long player_move_speed = 1000000000l;
		long player_dig_speed = 1000000000l;
		long guard_move_speed = player_move_speed * 2;
		long guard_climb_speed = guard_move_speed * 2;
		long guard_hole_speed = guard_move_speed * 5;
		long hole_speed = guard_move_speed * 8;
		
		for(PlayerCommandType type : PlayerCommandType.values())
		{
			if(type.isMoveType())
				pspeeds.put(type, player_move_speed);
			else if(type.isDigType())
				pspeeds.put(type, player_dig_speed);
		}
		
		for(GuardCommandType type : GuardCommandType.values())
		{
			if(type.isMoveType())
				gspeeds.put(type, guard_move_speed);
			else if(type.isClimbType())
				gspeeds.put(type, guard_climb_speed);
			else if(type == GuardCommandType.BLOCKING)
				gspeeds.put(type, guard_hole_speed);
		}
		
		default_speeds = new OperationsSpeeds(pspeeds, gspeeds, hole_speed);
	}

	@Override
	public long getHoleSpeed()
	{
		return hole_speed;
	}

}
