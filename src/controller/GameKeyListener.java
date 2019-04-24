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
	private IPlayerCommandAccepter accepter;
	private HumanPlayerGamePanel panel;
	
	public GameKeyListener(IHumanPlayerEngine engine, IPlayerCommandAccepter accepter, HumanPlayerGamePanel panel)
	{
		this.engine = engine;
		this.accepter = accepter;
		this.panel = panel;
	}
	
	public GameKeyListener(IHumanPlayerEngine engine, HumanPlayerGamePanel panel)
	{
		this(engine, new PlayerCommandAccepter(), panel);
	}
	
    @Override
    public void keyTyped(KeyEvent ke)
    {
    	
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        System.out.println(KeyEvent.getKeyText(ke.getKeyCode()));
        PlayerCommandType command = null;
        IPlayer player = engine.getState().getPool().getPlayer();
        
        boolean command_typed = true;
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
        default:
        	command_typed = false;
        }
        
        if(command_typed)
        {
            Set<PlayerCommandType> accepted = accepter.accept(player);
            
            if(accepted.contains(command))
            	engine.addCommand(command);
            else
                System.out.println("Player can't use command " + command);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        
    }
}