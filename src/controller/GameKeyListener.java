package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.services.IHumanPlayerEngine;
import model.services.PlayerCommand;

public class GameKeyListener implements KeyListener
{
	private IHumanPlayerEngine engine;
	
	public GameKeyListener(IHumanPlayerEngine engine)
	{
		this.engine = engine;
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
        PlayerCommand command = PlayerCommand.NEUTRAL;
        
        switch(ke.getKeyCode())
        {
        case 37:
        	command = PlayerCommand.LEFT;
        case 38:
        	command = PlayerCommand.UP;
        case 39:
        	command = PlayerCommand.RIGHT;
        case 40:
        	command = PlayerCommand.DOWN;
        case 68:
        	command = PlayerCommand.DIGLEFT;
        case 70:
        	command = PlayerCommand.DIGRIGHT;
        }
        
        engine.setCommand(command);
        
        // ideal would be to step every player_tick / guards_tick
        // according to their move speed (player is faster than guards)
        engine.stepPlayer();
        engine.stepGuards();
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        System.out.println("Released " + ke.getKeyChar());
    }
}