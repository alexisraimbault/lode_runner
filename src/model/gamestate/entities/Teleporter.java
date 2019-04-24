package model.gamestate.entities;

import model.services.ICell;
import model.services.ITeleporter;

public class Teleporter implements ITeleporter
{
	private ICell lcell;
	private ICell rcell;
	
	public Teleporter(ICell lcell, Cell rcell)
	{
		this.lcell = lcell;
		this.rcell = rcell;
	}

	@Override
	public ICell getFirstCell()
	{
		return lcell;
	}

	@Override
	public ICell getSecondCell()
	{
		return rcell;
	}
	

}
