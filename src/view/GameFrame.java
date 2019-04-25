package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.EditEnvironmentKeyListener;
import controller.EnvironmentLoader;
import controller.GameKeyListener;
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

	private IHumanPlayerEngine engine;
	private JPanel panel;
	
	public GameFrame() throws Exception
	{
	    this.setTitle("Lode Runner");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    //this.addKeyListener(new GameKeyListener(engine, panel));
	}
	
	public void startEdit(IHumanPlayerEngine engine) throws Exception
	{
		IEnvironment environment = engine.getState().getEnvironment();
	    this.setContentPane(new EditEnvironmentPanel(environment.getWidth(), environment.getHeight()));
	    this.getContentPane().setPreferredSize(new Dimension(environment.getWidth() * block_size, environment.getHeight() * block_size));
	    this.pack();
	    this.addKeyListener(new EditEnvironmentKeyListener((EditEnvironmentPanel) this.getContentPane()));
	}
	
	public void startGame(IHumanPlayerEngine engine, HumanPlayerGamePanel panel) throws Exception
	{
		this.engine = engine;
	    this.panel = panel;
	
		IEnvironment environment = engine.getState().getEnvironment();

	    this.setContentPane(panel);
	    this.getContentPane().setPreferredSize(new Dimension(environment.getWidth() * block_size, environment.getHeight() * block_size));
	    this.pack();
	    this.addKeyListener(new GameKeyListener(engine, panel));
	}
}
