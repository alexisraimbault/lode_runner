package decorator.entities;

import model.gamestate.entities.Teleporter;
import model.services.ICell;
import model.services.ITeleporter;

public class TeleporterDecorator implements ITeleporter
{
	Teleporter delegate;
	
	public TeleporterDecorator(Teleporter d){
		delegate = d;
	}

	public ICell getFirstCell() {
		return delegate.getFirstCell();
	}

	public ICell getSecondCell() {
		return delegate.getSecondCell();
	}
}
