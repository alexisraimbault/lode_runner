package model.gamestate.entities;

import model.services.ICell;
import model.services.IEnvironment;
import model.services.IItem;

public abstract class Item implements IItem
{
	private ICell cell;
	
	public Item(ICell cell)
	{
		this.cell = cell;
	}
	
	@Override
	public ICell getCell()
	{
		return cell;
	}

	@Override
	public IEnvironment getEnvironment()
	{
		return cell.getEnvironment();
	}

	@Override
	public int getX()
	{
		return cell.getX();
	}

	@Override
	public int getY()
	{
		return cell.getY();
	}
}
