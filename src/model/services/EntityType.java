package model.services;

public enum EntityType
{
	PLAYER,
	GUARD,
	FANTOM,
	TREASURE,
	TELEPORTER;
	
	public boolean isCharacter()
	{
		return this == PLAYER || this == GUARD || this == FANTOM;
	}
	
	public boolean isItem()
	{
		return !isCharacter();
	}
}
