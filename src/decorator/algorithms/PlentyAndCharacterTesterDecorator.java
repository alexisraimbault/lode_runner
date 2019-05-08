package decorator.algorithms;

import model.services.ICell;
import model.services.IPlentyAndCharacterTester;
import model.services.IPlentyTester;

public class PlentyAndCharacterTesterDecorator implements IPlentyAndCharacterTester{
	IPlentyAndCharacterTester delegate;
	public PlentyAndCharacterTesterDecorator(IPlentyAndCharacterTester d)
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
