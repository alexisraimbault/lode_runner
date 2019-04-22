package controller;

public class TimeConverter
{
	private float ref_units;
	private float ref_time;
	
	public TimeConverter(int ref_units, float ref_time)
	{
		this.ref_units = (float)ref_units;
		this.ref_time = ref_time;
	}
	
	public float getTime(int nb_units)
	{
		return (nb_units / ref_units) * ref_time;
	}
}
