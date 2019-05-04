package decorator.environment;

import model.services.IContent;
import model.services.IEnvironment;
import model.services.Nature;

public class EnvironmentDecorator implements IEnvironment{
	protected IEnvironment delegate;
	
	public EnvironmentDecorator(IEnvironment d) {
		delegate = d;
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public IContent getCellContent(int x, int y) {
		return delegate.getCellContent(x, y);
	}

	public int getHeight() {
		return delegate.getHeight();
	}

	public Nature getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}

	public void dig(int x, int y) {
		delegate.dig(x, y);
	}

	public void fill(int x, int y) {
		delegate.fill(x, y);
	}
}
