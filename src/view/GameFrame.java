package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.EnvironmentLoader;
import controller.GameKeyListener;
import controller.TimeConverter;
import model.HumanPlayerEngine;
import model.gamestate.GameState;
import model.gamestate.environment.Environment;
import model.services.IEditableEnvironment;
import model.services.IEnvironment;
import model.services.IEnvironmentLoader;
import model.services.IGameState;
import model.services.IHumanPlayerEngine;

public class GameFrame extends JFrame
{
	private static final int block_size = 50;
	
	public GameFrame(IHumanPlayerEngine engine, HumanPlayerGamePanel panel, TimeConverter converter) throws Exception
	{
		IEnvironment environment = engine.getState().getEnvironment();
		
	    this.setTitle("Lode Runner");
	    this.setContentPane(panel);
	    this.getContentPane().setPreferredSize(new Dimension(environment.getWidth() * block_size, environment.getHeight() * block_size));
	    this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    this.addKeyListener(new GameKeyListener(engine, panel, converter));
	}
}
