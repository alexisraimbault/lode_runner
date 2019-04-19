package view;

import javax.swing.JFrame;

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
	
	public GameFrame(IHumanPlayerEngine engine) throws Exception
	{
		this.engine = engine;
		
		IEnvironment environment = engine.getState().getEnvironment();
		
	    this.setTitle("Lode Runner");
	    this.setSize(environment.getWidth() * block_size, environment.getHeight() * block_size);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(new HumanPlayerGamePanel(engine));
	    this.setVisible(true);
	    this.addKeyListener(new GameKeyListener(engine));
	}
}
