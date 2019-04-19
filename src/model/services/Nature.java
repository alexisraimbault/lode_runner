package model.services;

public enum Nature
{
	EMPTY,
	PLATFORM,
	HOLE,
	LADDER,
	HANDRAIL,
	METAL;
	
	public boolean isPlenty()
	{
		return this == METAL || this == PLATFORM;
	}
}
