package decorator.environment;

import model.services.IContent;
import model.services.IDynamicEnvironment;
import model.services.IEditableEnvironment;
import model.services.Nature;

public class EditableEnvironmentDecorator implements IEditableEnvironment{
	protected IEditableEnvironment delegate;
	
	public EditableEnvironmentDecorator(IEditableEnvironment d) {
		delegate = d;
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public int getHeight() {
		return delegate.getHeight();
	}

	public IContent getCellContent(int x, int y) {
		return delegate.getCellContent(x, y);
	}

	public IDynamicEnvironment produce() {
		return delegate.produce();
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
