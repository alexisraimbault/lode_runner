package decorator.environment;

import model.services.IScreen;
import model.services.Nature;

public class ScreenDecorator implements IScreen{
	private IScreen delegate;
	public ScreenDecorator(IScreen d ){
		delegate = d;
	}
	public int getWidth() {
		return delegate.getWidth();
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
