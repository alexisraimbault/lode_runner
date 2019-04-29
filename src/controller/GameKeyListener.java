package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import javax.swing.JPanel;

import model.algorithms.PlayerCommandAccepter;
import model.services.IHumanPlayerEngine;
import model.services.IPlayer;
import model.services.IPlayerCommandAccepter;
import model.services.PlayerCommandType;
import view.HumanPlayerGamePanel;

public class GameKeyListener implements KeyListener
{
	private IHumanPlayerEngine engine;
	private HumanPlayerGamePanel panel;
	private TimeConverter converter;
	private Thread tick_thread;
	
	public GameKeyListener(IHumanPlayerEngine engine, HumanPlayerGamePanel panel, TimeConverter converter)
	{
		this.engine = engine;
		this.panel = panel;
		this.converter = converter;
		this.tick_thread = null;
	}
	
    @Override
    public void keyTyped(KeyEvent ke)
    {
    	
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        //System.out.println(KeyEvent.getKeyText(ke.getKeyCode()));
    	
        PlayerCommandType command = null;
        
        boolean command_typed = (tick_thread != null);
        switch(ke.getKeyCode())
        {
        case 37:
        	command = PlayerCommandType.LEFT;
        	break;
        case 38:
        	command = PlayerCommandType.UP;
        	break;
        case 39:
        	command = PlayerCommandType.RIGHT;
        	break;
        case 40:
        	command = PlayerCommandType.DOWN;
        	break;
        case 68:
        	command = PlayerCommandType.DIGLEFT;
        	break;
        case 70:
        	command = PlayerCommandType.DIGRIGHT;
        	break;
        case ' ':
        	if(tick_thread == null)
        	{
        		tick_thread = new Thread(new GameRunner(engine, panel, converter));
        		tick_thread.start();
        	}
        	else
        	{
        		engine.stop();
        		try
				{
					tick_thread.join();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
        		tick_thread = null;
        	}
    		break;
        default:
        	command_typed = false;
        }
        
        if(command_typed)
        {
            engine.addCommand(command);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
    	
    }
}