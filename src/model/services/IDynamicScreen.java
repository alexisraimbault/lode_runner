package model.services;

public interface IDynamicScreen extends IDynamicSize
{
	public Nature getCellNature(int x, int y);
	
	public void setCellNature(int x, int y, Nature nature);
}
