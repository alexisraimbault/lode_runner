package decorator.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IContent;
import model.services.IEntity;
import model.services.IEnvironment;
import model.services.Nature;

public class EntityDecorator implements IEntity{
	protected IEntity delegate;
	
	public EntityDecorator(IEntity d){
		delegate = d;
	}

	public IEnvironment getEnvironment() {
		return delegate.getEnvironment();
	}

	public EntityType getType() {
		return delegate.getType();
	}

	public int getX() {
		return delegate.getX();
	}

	public void setPosition(ICell cell) {
		delegate.setPosition(cell);
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

	public boolean equals(ICell other) {
		return delegate.equals(other);
	}

	public void setPosition(int x, int y) {
		delegate.setPosition(x, y);
	}

	public void setX(int x) {
		delegate.setX(x);
	}

	public void setY(int y) {
		delegate.setY(y);
	}

}
