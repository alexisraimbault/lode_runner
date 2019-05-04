package decorator.gameState;

import model.services.EntityType;
import model.services.ICell;
import model.services.IContent;
import model.services.IEnvironment;
import model.services.IExecutedCharacterOperation;
import model.services.IOperationEntity;
import model.services.Nature;

public class OperationEntityDecorator<OperationType extends Enum<OperationType>> implements IOperationEntity<OperationType>{
	protected IOperationEntity<OperationType> delegate;
	
	public OperationEntityDecorator(IOperationEntity<OperationType> d) {
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

	public boolean hasOperation() {
		return delegate.hasOperation();
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

	public IExecutedCharacterOperation<OperationType> getExecutedOperation() {
		return delegate.getExecutedOperation();
	}

	public void setPosition(int x, int y) {
		delegate.setPosition(x, y);
	}

	public void setExecutedOperation(IExecutedCharacterOperation<OperationType> operation) {
		delegate.setExecutedOperation(operation);
	}

	public void setX(int x) {
		delegate.setX(x);
	}

	public void setY(int y) {
		delegate.setY(y);
	}

	public void setNoOperation() {
		delegate.setNoOperation();
	}

	public void update(long elapsed) {
		delegate.update(elapsed);
	}

}
