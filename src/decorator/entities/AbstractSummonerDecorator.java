package decorator.entities;

import model.services.IEntity;
import model.services.ISummoner;

public class AbstractSummonerDecorator <Entity extends IEntity> implements ISummoner<Entity>
{
	
	protected ISummoner<Entity> delegate;
	
	public AbstractSummonerDecorator(ISummoner<Entity> d ){
		delegate = d;
	}

	public boolean hasInstance() {
		return delegate.hasInstance();
	}

	public Entity getInstance() {
		return (Entity) delegate.getInstance();
	}

	public void respawn(IEntity instance) {
		delegate.respawn((Entity) instance);
	}

	public void destroy() {
		delegate.destroy();
	}

	public int hashCode() {
		return delegate.hashCode();
	}

	public String toString() {
		return delegate.toString();
	}



}
