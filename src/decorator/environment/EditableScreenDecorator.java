package decorator.environment;

import model.services.IDynamicScreen;
import model.services.IEditableScreen;
import model.services.IScreen;
import model.services.Nature;

public class EditableScreenDecorator implements IEditableScreen {
	private IEditableScreen delegate;
	public EditableScreenDecorator(IEditableScreen d ){
		delegate = d;
	}
	public int getWidth() {
		return delegate.getWidth();
	}
	public IDynamicScreen produce() {
		return delegate.produce();
	}
	public int getHeight() {
		return delegate.getHeight();
	}
	public Nature getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}
	public boolean isPlayable() {
		return delegate.isPlayable();
	}
	public void setCellNature(int x, int y, Nature nature) {
		delegate.setCellNature(x, y, nature);
	}
	public void resize(int width, int height) {
		delegate.resize(width, height);
	}
}
