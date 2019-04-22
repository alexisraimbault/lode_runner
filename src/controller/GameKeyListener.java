package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

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
        System.out.println("Typed " + ke.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        System.out.println("Pressed " + ke.getKeyChar());
        PlayerCommandType command = PlayerCommandType.NEUTRAL;
        IPlayer player = engine.getState().getPool().getPlayer();
        
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
        }
        
        Set<PlayerCommandType> accepted = accepter.accept(player);
        System.out.println(accepted);
        
        if(accepted.contains(command))
        	engine.setCommand(command);
        else
            System.out.println("Player can't use command " + command);
        
        // ideal would be to step every player_tick / guards_tick
        // according to their move speed (player is faster than guards)
        engine.stepPlayer();
        //engine.stepGuards();
        panel.repaint();
        
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        System.out.println("Released " + ke.getKeyChar());
    }
}