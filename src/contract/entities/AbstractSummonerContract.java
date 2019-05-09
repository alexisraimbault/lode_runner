package contract.entities;

import decorator.entities.AbstractSummonerDecorator;
import model.services.IEntity;
import model.services.ISummoner;

public class AbstractSummonerContract<Entity extends IEntity> extends AbstractSummonerDecorator <Entity>{

	public AbstractSummonerContract(ISummoner<Entity> d) {
		super(d);
		checkInvariant();
	}

	public void checkInvariant() {
		
	}

}
