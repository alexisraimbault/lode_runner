package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import model.services.EntityType;
import model.services.IEditableEnvironment;
import model.services.Nature;
import view.EditEnvironmentPanel;
import view.SelectGamePanel;

public class SelectMapKeyListener implements KeyListener
{
	private SelectGamePanel selectPanel;
	
	public SelectMapKeyListener(SelectGamePanel selectPanel)
	{
		this.selectPanel = selectPanel;
	}
	
    @Override
    public void keyTyped(KeyEvent ke)
    {
    	
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        System.out.println(ke.getKeyCode());
        if(selectPanel.isActiveSelection()){
            
            switch(ke.getKeyCode())
            {
            case 10://ENTER -> validate selection
            	selectPanel.validateSelection();
            	break;
            default:
            	break;
            }
        }
    }
        

    @Override
    public void keyReleased(KeyEvent ke)
    {
        
    }
}