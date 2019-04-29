package view;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.EditEnvironmentKeyListener;
import controller.GameKeyListener;
import controller.TimeConverter;
import model.HumanPlayerEngine;
import model.gamestate.GameState;
import controller.GameRunner;
import controller.SelectMapKeyListener;
import model.gamestate.environment.DynamicScreen;
import model.gamestate.environment.EditableEnvironment;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.IEditableEnvironment;
import model.services.IEnvironment;
import model.services.IGameState;
import model.services.IHumanPlayerEngine;
import model.services.PlayerCommandType;
import model.services.Status;

public class GameFrame extends JFrame
{
	private static final int block_size = 50;
	
	private KeyListener k;
	

	
	/*
	 * Dès qu'on démarre une partie, on génère un moteur de jeu
	 */
	public IHumanPlayerEngine getDefaultEngine(IEnvironment environment)
	{
		final int nb_lives = 3;
		IGameState state = new GameState(environment, OperationsSpeeds.default_speeds, nb_lives);
		return new HumanPlayerEngine(state);
	}
	
	/*
	 * Conversion du temps par défaut
	 */
	public TimeConverter getDefaultConverter(IHumanPlayerEngine engine)
	{
		final long player_move_speed = engine.getState().getSpeeds().get(PlayerCommandType.LEFT);
		final long player_move_nano_time = 200000000; // the time reference is 200 ms for player speed
		return new TimeConverter(player_move_speed, player_move_nano_time);
	}
	
	
	
	
	public GameFrame() throws Exception
	{
	    this.setTitle("Lode Runner");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    
	    startEdit(new EditableEnvironment(new DynamicScreen()));
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
		IHumanPlayerEngine engine = getDefaultEngine(environment);
		TimeConverter converter = getDefaultConverter(engine);
		
		HumanPlayerGamePanel panel = new HumanPlayerGamePanel(engine);
		
		if(k != null)
			this.removeKeyListener(k);

	    this.setContentPane(panel);
	    this.getContentPane().setPreferredSize(new Dimension(environment.getWidth() * block_size, environment.getHeight() * block_size));
	    this.pack();
	    this.k = new GameKeyListener(engine, panel, converter);
	    this.addKeyListener(k);
	}
}
