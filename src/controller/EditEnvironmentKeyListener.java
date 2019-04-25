package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import model.algorithms.PlayerCommandAccepter;
import model.services.IEditableEnvironment;
import model.services.IHumanPlayerEngine;
import model.services.IPlayer;
import model.services.Nature;
import model.services.IPlayerCommandAccepter;
import model.services.PlayerCommandType;
import view.EditEnvironmentPanel;
import view.HumanPlayerGamePanel;

public class EditEnvironmentKeyListener implements KeyListener
{
	private IEditableEnvironment editable;
	private EditEnvironmentPanel editionPanel;
	
	public EditEnvironmentKeyListener(EditEnvironmentPanel editionPanel)
	{
		this.editionPanel = editionPanel;
		this.editable = editionPanel.getEditable();
	}
	
    @Override
    public void keyTyped(KeyEvent ke)
    {
    	
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        System.out.println(ke.getKeyCode());
        if(editionPanel.isActiveSelection()){
        	Nature mat = null;
            boolean edit_typed = false;
            switch(ke.getKeyCode())
            {
            case 77://M -> metal
            	edit_typed = true;
            	mat = Nature.METAL;
            	break;
            case 80://P -> platform
            	edit_typed = true;
            	mat = Nature.PLATFORM;
            	break;
            case 76://L -> ladder
            	edit_typed = true;
            	mat = Nature.LADDER;
            	break;
            case 69://E -> empty
            	edit_typed = true;
            	mat = Nature.EMPTY;
            	break;
            case 72://H -> handrail
            	edit_typed = true;
            	mat = Nature.HANDRAIL;
            	break;
            default:
            	edit_typed = false;
            }
            if(edit_typed){
            	editionPanel.edit(mat);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        
    }
}