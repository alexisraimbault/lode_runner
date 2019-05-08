package decorator.algorithms;

import model.services.ICell;
import model.services.IPlentyAndHoleTester;
import model.services.IPlentyTester;

public class PlentyAndHoleTesterDecorator implements IPlentyAndHoleTester{
	IPlentyAndHoleTester delegate;
	public PlentyAndHoleTesterDecorator(IPlentyAndHoleTester d)
	{
		delegate = d;
	}
	
	public boolean test(ICell cell) {
		return delegate.test(cell);
	}

	public IPlentyTester getPlenty_tester() {
		return delegate.getPlenty_tester();
	}
}
