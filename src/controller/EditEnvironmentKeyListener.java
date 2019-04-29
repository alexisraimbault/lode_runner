package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Set;

import model.algorithms.PlayerCommandAccepter;
import model.services.IEditableEnvironment;
import model.services.IHumanPlayerEngine;
import model.services.EntityType;
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
        	EntityType entity = null;
        	boolean entity_typed = false;
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
            case 83://S -> PLAYER spawn
            	entity_typed = true;
            	entity = EntityType.PLAYER;
            	break;
            case 71://G -> GUARD
            	entity_typed = true;
            	entity = EntityType.GUARD;
            	break;
            case 70://F -> FANTOM
            	entity_typed = true;
            	entity = EntityType.FANTOM;
            	break;
            case 84://T -> TREASURE
            	entity_typed = true;
            	entity = EntityType.TREASURE;
            	break;
            case 74://J -> TELEPORTER
            	entity_typed = true;
            	entity = EntityType.TELEPORTER;
            	break;
            default:
            	entity_typed = false;
            	edit_typed = false;
            }
            
            if(edit_typed)
            	editionPanel.editEnv(mat);
            if(entity_typed)
            	editionPanel.editEntity(entity);
        }
        if(ke.getKeyCode() == 16){//SHIFT -> save map
			try {
				editionPanel.saveMap("environment_test");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if(ke.getKeyCode() == 10){//ENTER -> start game
			try {
				editionPanel.startGame();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
        

    @Override
    public void keyReleased(KeyEvent ke)
    {
        
    }
}