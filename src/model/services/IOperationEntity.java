package model.services;

public interface IOperationEntity<OperationType extends Enum<OperationType>> extends ICharacter
{
	/*
	 * init:
	 * 	!hasOperation()
	 */
	public boolean hasOperation();
	
	/*
	 * pre:
	 * 	hasOperation()
	 */
	public IExecutedCharacterOperation<OperationType> getExecutedOperation();
	
	/*
	 * pre:
	 * 	!hasOperation()
	 * 	operation.getElapsedTime() == 0
	 * 
	 * post:
	 * 	hasOperation()
	 * 	getExecutedOperation() == operation
	 */
	public void setExecutedOperation(IExecutedCharacterOperation<OperationType> operation);
	
	/*
	 * pre:
	 * 	getOperation().isEnded()
	 * 
	 * post:
	 * 	!hasOperation()
	 */
	public void setNoOperation();
	
	/*
	 * pre:
	 * 	pre getOperation().update(elapsed)
	 * 	(i.e. elapsed >= 0, !hasOperation(), getOperation().isEnded())
	 */
	public void update(long elapsed);
	
	/*
	 * invariants:
	 * 	hasOperation() <=> !getOperation().isEnded()
	 */
}
