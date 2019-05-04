package decorator.entities;

import model.services.ICell;
import model.services.ITeleporter;

public class TeleporterDecorator implements ITeleporter
{
	protected ITeleporter delegate;
	
	public TeleporterDecorator(ITeleporter d){
		delegate = d;
	}

	public ICell getFirstCell() {
		return delegate.getFirstCell();
	}

	public ICell getSecondCell() {
		return delegate.getSecondCell();
	}
}
