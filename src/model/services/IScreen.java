package model.services;

public interface IScreen extends IStaticSize
{
	public Nature getCellNature(int x, int y);
	
	public void dig(int x, int y);
	public void fill(int x, int y);
}
