package decorator.environment;

import model.services.EntityType;
import model.services.IContent;

public class ContentDecorator implements IContent{
	IContent delegate;
	public ContentDecorator(IContent d){
		delegate = d;
	}
	public int counts(EntityType type) {
		return delegate.counts(type);
	}
	public boolean contains(EntityType type) {
		return delegate.contains(type);
	}
	public boolean isEmpty() {
		return delegate.isEmpty();
	}
	public int size() {
		return delegate.size();
	}
	public int nbCharacters() {
		return delegate.nbCharacters();
	}
	public int nbItems() {
		return delegate.nbItems();
	}
	public void add(EntityType type) {
		delegate.add(type);
	}
	public void add(EntityType type, int occ) {
		delegate.add(type, occ);
	}
	public void remove(EntityType type) {
		delegate.remove(type);
	}
	public void remove(EntityType type, int occ) {
		delegate.remove(type, occ);
	}
	public void clear() {
		delegate.clear();
	}

}
