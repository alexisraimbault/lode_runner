package controller;

public class TimeConverter
{
	private long ref_units;
	private long ref_nano;
	
	public TimeConverter(long player_move_speed, long ref_nano)
	{
		this.ref_units = player_move_speed;
		this.ref_nano = ref_nano;
	}
	
	public long getNanoTimeFromUnits(long nb_units)
	{
		return (long) (nb_units * ref_nano / ref_units);
	}
	
	public int getUnitsFromNanoTime(long nb_nano)
	{
		return (int) (nb_nano * ref_units / ref_nano);
	}
}
