package decorator.entities;

import model.services.ICell;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.Nature;

public class CellDecorator implements ICell
{
	protected ICell delegate;
	
	public CellDecorator(ICell d){
		delegate = d;
	}

	public IEnvironment getEnvironment() {
		return delegate.getEnvironment();
	}

	public int getX() {
		return delegate.getX();
	}

	public int getY() {
		return delegate.getY();
	}

	public Nature getNature() {
		return delegate.getNature();
	}

	public IContent getContent() {
		return delegate.getContent();
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public boolean equals(ICell cell) {
		return delegate.equals(cell);
	}

	public String toString() {
		return delegate.toString();
	}

	
}
