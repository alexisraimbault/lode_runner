package decorator.entities;

import model.services.IExecutedCharacterOperation;
import model.services.IExecutedOperation;
import model.services.IOperationEntity;

public class AbstractOperatingEntityDecorator<OperationType extends Enum<OperationType>> extends AbstractEntityDecorator implements IOperationEntity<OperationType>
{

	public AbstractOperatingEntityDecorator(IOperationEntity<OperationType> d) {
		super(d);
	}

	@Override
	public IExecutedCharacterOperation<OperationType> getExecutedOperation() {
		return ((IOperationEntity<OperationType>) delegate).getExecutedOperation();
	}

	@Override
	public boolean hasOperation() {
		// TODO Auto-generated method stub
		return  ((IOperationEntity<OperationType>) delegate).hasOperation() ;
	}

	@Override
	public void setExecutedOperation(IExecutedCharacterOperation<OperationType> operation) {
		 ((IOperationEntity<OperationType>) delegate).setExecutedOperation(operation);
		
	}

	@Override
	public void setNoOperation() {
		 ((IOperationEntity<OperationType>) delegate).setNoOperation();
		
	}

	@Override
	public void update(long elapsed) {
		 ((IExecutedOperation) delegate).update(elapsed);
		
	}


}
