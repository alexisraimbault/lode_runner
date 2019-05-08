package decorator.algorithms;

import model.services.ICell;
import model.services.IPlentyTester;

public class PlentyTesterDecorator implements IPlentyTester{
	IPlentyTester delegate;
	public PlentyTesterDecorator(IPlentyTester d)
	{
		delegate = d;
	}
	
	public boolean test(ICell cell) {
		return delegate.test(cell);
	}
}
