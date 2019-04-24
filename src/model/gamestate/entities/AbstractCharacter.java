package model.gamestate.entities;

import model.services.ICell;
import model.services.ICharacter;
import model.services.IEnvironment;

public abstract class AbstractCharacter implements ICharacter
{
	private ICell cell;
	
	public AbstractCharacter(ICell cell)
	{
		this.cell = cell;
	}
	
	@Override
	public ICell getCell()
	{
		return cell;
	}
	
	@Override
	public void setCell(ICell cell)
	{
		this.cell.getContent().remove(getType());
		this.cell = cell;
		this.cell.getContent().add(getType());
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

	@Override
	public void setPosition(int x, int y)
	{
		setCell(new Cell(getEnvironment(), x, y));
	}

	@Override
	public void setX(int x)
	{
		setPosition(x, getY());
	}

	@Override
	public void setY(int y)
	{
		setPosition(getX(), y);
	}
	
}
