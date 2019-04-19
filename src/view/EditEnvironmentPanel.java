package view;

import javax.swing.JPanel;

import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.services.IEditableEnvironment;

public class EditEnvironmentPanel extends JPanel
{
	private IEditableEnvironment editable;
	
	public EditEnvironmentPanel(int width, int height)
	{
		editable = new EditableEnvironment(new DynamicScreen());
		editable.resize(width, height);
	}
}
