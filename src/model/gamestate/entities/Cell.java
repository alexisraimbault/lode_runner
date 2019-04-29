package model.gamestate.entities;

import model.services.ICell;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.MoveType;
import model.services.Nature;

public class Cell implements ICell
{
	
	private IEnvironment environment;
	private int x;
	private int y;
	
	public Cell(IEnvironment environment, int x, int y)
	{
		this.environment = environment;
		this.x = x;
		this.y = y;
	}
	
	public Cell(ICell cell)
	{
		this(cell.getEnvironment(), cell.getX(), cell.getY());
	}
	
	@Override
	public IEnvironment getEnvironment()
	{
		return environment;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public Nature getNature()
	{
		return environment.getCellNature(x, y);
	}

	@Override
	public IContent getContent()
	{
		return environment.getCellContent(x, y);
	}
	
	public static boolean hasNext(ICell cell, MoveType type)
	{
		switch(type)
		{
		case LEFT:
			return cell.getX() > 0;
		case RIGHT:
			return cell.getX() < cell.getEnvironment().getWidth() - 1;
		case DOWN:
			return cell.getY() > 0;
		case UP:
			return cell.getY() < cell.getEnvironment().getHeight() - 1;
		}
		return false;
	}
	
	public static ICell getNext(ICell cell, MoveType type)
	{
		IEnvironment environment = cell.getEnvironment();
		int x = cell.getX();
		int y = cell.getY();
		
		switch(type)
		{
		case LEFT:
			return new Cell(environment, x - 1, y);
		case RIGHT:
			return new Cell(environment, x + 1, y);
		case DOWN:
			return new Cell(environment, x, y - 1);
		case UP:
			return new Cell(environment, x, y + 1);
		default:
			break;
		}
		return null;
	}
	
	@Override
	public boolean equals(ICell cell)
	{
		return getX() == cell.getX() && getY() == cell.getY() && getEnvironment() == cell.getEnvironment();
	}

}
