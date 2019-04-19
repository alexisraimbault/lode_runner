package model.services;

public enum Status
{
	PLAYING,
	PAUSE,
	WIN,
	LOSS;
	
	public boolean isEnd()
	{
		return this == WIN || this == LOSS;
	}
}
