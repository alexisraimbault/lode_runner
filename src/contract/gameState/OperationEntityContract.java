package contract.gameState;

import contract.contracterr.InitError;
import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.gameState.OperationEntityDecorator;
import model.services.IExecutedCharacterOperation;
import model.services.IOperationEntity;

public class OperationEntityContract<OperationType extends Enum<OperationType>> extends OperationEntityDecorator<OperationType> {
	public OperationEntityContract(IOperationEntity<OperationType> d) {
		super(d);
		checkInvariant();
		checkInit();
	}

	/*
	 * init:
	 * 	!hasOperation()
	 */
	public void checkInit(){
		if( hasOperation())
			throw new InitError("in OperationEntity : should have no operation set");
	}
	
	public boolean hasOperation(){
		return hasOperation();
	}

	public IExecutedCharacterOperation<OperationType> getExecutedOperation(){
		if( !hasOperation())
			throw new PreconditionError("in OperationEntity -> IExecutedCharacterOperation : no operation set");
		checkInvariant();
		IExecutedCharacterOperation<OperationType> res = super.getExecutedOperation();
		checkInvariant();
		return res;
	}
	

	public void setExecutedOperation(IExecutedCharacterOperation<OperationType> operation){
		if(hasOperation())
			throw new PreconditionError("in OperationEntity -> setExecutedOperation : an operation is already being done");
		if(!(operation.getElapsedTime() == 0))
			throw new PreconditionError("in OperationEntity -> setExecutedOperation : the operation to set has an elapsed time not equal to 0");
		checkInvariant();
		super.setExecutedOperation( operation);
		checkInvariant();
		if(!hasOperation())
			throw new PostconditionError("in OperationEntity -> setExecutedOperation : should have an operation after setExecutedOperation");
		if(!(getExecutedOperation() == operation))
			throw new PostconditionError("in OperationEntity -> setExecutedOperation : the operation that has been set is not the right one");
	}
	

	public void setNoOperation(){
		if(!(getExecutedOperation().isEnded()))
			throw new PreconditionError("in OperationEntity -> setNoOperation : the current operation has not ended");
		checkInvariant();
		super.setNoOperation();
		checkInvariant();
		if(hasOperation())
			throw new PostconditionError("in OperationEntity -> setNoOperation : should not have an operation after setNoOperation");
	}

	public void update(long elapsed){
		if(!(elapsed >= 0 && !hasOperation() && getExecutedOperation().isEnded()))
			throw new PreconditionError("in OperationEntity -> update : cant update with elapsed at the current operation status");
		checkInvariant();
		super.update(elapsed);
		checkInvariant();
		
	}

	public void checkInvariant() {
		if(!(hasOperation() == !getExecutedOperation().isEnded()))
			throw new InvariantError("the current operation status is not according to the operation status");
	}
}