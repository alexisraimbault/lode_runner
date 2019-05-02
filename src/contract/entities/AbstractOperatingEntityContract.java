package contract.entities;

import decorator.entities.AbstractOperatingEntityDecorator;
import model.services.IOperationEntity;

public class AbstractOperatingEntityContract<OperationType extends Enum<OperationType>> extends AbstractOperatingEntityDecorator<OperationType> {

	public AbstractOperatingEntityContract(IOperationEntity<OperationType> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void checkInvariant() {
		//TODO
	}

}
