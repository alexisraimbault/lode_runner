package decorator.algorithms;

import model.services.ICell;
import model.services.IPlentyAndGuardsTester;

public class PlentyAndGuardsTesterDecorator implements IPlentyAndGuardsTester{
	IPlentyAndGuardsTester delegate;
	public PlentyAndGuardsTesterDecorator(IPlentyAndGuardsTester d)
	{
		delegate = d;
	}
	
	public boolean test(ICell cell) {
		return delegate.test(cell);
	}
}
