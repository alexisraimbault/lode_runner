package view;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.EditEnvironmentKeyListener;
import controller.GameKeyListener;
import controller.GameRunner;
import controller.SelectMapKeyListener;
import model.gamestate.environment.Environment;
import model.services.IEditableEnvironment;
import model.services.IEnvironment;
import model.services.IHumanPlayerEngine;
import model.services.Status;

public class GameFrame extends JFrame
{
	private static final int block_size = 50;
	private IHumanPlayerEngine engine;
	private KeyListener k;
	private GameRunner gr;
	
	public GameFrame(IHumanPlayerEngine engine,  GameRunner gr) throws Exception
	{
		this.gr = gr;
		this.engine = engine;
	    this.setTitle("Lode Runner");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	}
	
	public void startEdit(IEditableEnvironment editable) throws Exception
	{
		if(k != null)
			this.removeKeyListener(k);
	    this.setContentPane(new EditEnvironmentPanel(editable, this));
	    this.getContentPane().setPreferredSize(new Dimension(editable.getWidth() * block_size, editable.getHeight() * block_size));
	    this.pack();
	    this.k = new EditEnvironmentKeyListener((EditEnvironmentPanel) this.getContentPane());
	    this.addKeyListener(k);
	}
	
	public void startGame(IHumanPlayerEngine engine, HumanPlayerGamePanel panel) throws Exception
	{
		if(k != null)
			this.removeKeyListener(k);
		this.engine = engine;
	
		IEnvironment environment = engine.getState().getEnvironment();

	    this.setContentPane(panel);
	    this.getContentPane().setPreferredSize(new Dimension(environment.getWidth() * block_size, environment.getHeight() * block_size));
	    this.pack();
	    this.k = new GameKeyListener(engine, panel);
	    this.addKeyListener(k);
	}
	
	public void startSelection() throws Exception
	{
		if(k != null)
			this.removeKeyListener(k);
	    this.setContentPane(new SelectGamePanel(this));
	    this.getContentPane().setPreferredSize(new Dimension(20 * block_size, 20 * block_size));
	    this.pack();
	    this.k = new SelectMapKeyListener((SelectGamePanel) this.getContentPane());
	    this.addKeyListener(k);
	}
	
	public void launchGame(IEditableEnvironment editable) throws Exception{
		if(!editable.isPlayable())
			try {
				throw new Exception("The loaded environment is not playable");
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		IEnvironment environment = new Environment(editable.produce());
		engine.getState().setEnvironment(environment);
		if(k != null)
			this.removeKeyListener(k);
		HumanPlayerGamePanel panel = new HumanPlayerGamePanel(engine);
	    this.setContentPane(panel);
	    gr.setPanel(panel);
	    this.getContentPane().setPreferredSize(new Dimension(environment.getWidth() * block_size, environment.getHeight() * block_size));
	    this.pack();
	    this.k = new GameKeyListener(engine, panel);
	    this.addKeyListener(k);
		engine.start();
	}
}
