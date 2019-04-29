package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.HumanPlayerEngine;
import model.gamestate.GameState;
import model.gamestate.environment.Environment;
import model.gamestate.operations.OperationsSpeeds;
import model.services.IEditableEnvironment;
import model.services.IEnvironment;
import model.services.IEnvironmentLoader;
import model.services.IGameState;
import model.services.IHumanPlayerEngine;
import model.services.PlayerCommandType;
import view.GameFrame;
import view.HumanPlayerGamePanel;

public class LodeRunner extends JFrame
{
	/*public void launchGame(IEditableEnvironment editable, GameFrame gf) throws Exception{
		if(!editable.isPlayable())
			throw new Exception("The loaded environment is not playable");
	
		IEnvironment environment = new Environment(editable.produce());
		IGameState state = new GameState(environment, OperationsSpeeds.default_speeds);
	
		IHumanPlayerEngine engine = new HumanPlayerEngine(state);
		HumanPlayerGamePanel panel = new HumanPlayerGamePanel(engine);
		
		gf.startGame(engine, panel);
		long player_move_speed = state.getSpeeds().get(PlayerCommandType.LEFT);
		// means player move speed last 500 ms
		TimeConverter converter = new TimeConverter(player_move_speed, 200000000);
		
		Thread tick_thread = new Thread(new GameRunner(engine, panel, converter));
		
		tick_thread.start();
		
		tick_thread.join();
		
	}*/
	public static void main(String[] args)
	{
		try
		{
			
			if(args.length != 1)
				throw new Exception("Expected file_path argument");
			

			IEnvironmentLoader loader = new EnvironmentLoader();
			IEditableEnvironment editable = loader.loadFromFile(args[0]);
			
			if(!editable.isPlayable())
				throw new Exception("The loaded environment is not playable");
			
			IEnvironment environment = new Environment(null);
			IGameState state = new GameState(null, OperationsSpeeds.default_speeds);

			IHumanPlayerEngine engine = new HumanPlayerEngine(state);
			//HumanPlayerGamePanel panel = new HumanPlayerGamePanel(engine);
			
			
			
			
			long player_move_speed = state.getSpeeds().get(PlayerCommandType.LEFT);
			// means player move speed last 500 ms
			TimeConverter converter = new TimeConverter(player_move_speed, 200000000);
			GameRunner gr = new GameRunner(engine, converter);
			
			Thread tick_thread = new Thread(gr);
			GameFrame frame = new GameFrame(engine, gr);
			frame.startSelection();
			//frame.startGame(engine, panel);	//uncomment this line for playing and not editing ( only for debug of course )
			tick_thread.start();   //uncomment this line for playing and not editing ( only for debug of course )
			
			tick_thread.join();   //uncomment this line for playing and not editing ( only for debug of course )
			
			//frame.startEdit(engine);//comment this line for playing and not editing ( only for debug of course )
			
			// TODO ask to exit the game
			
			
			
			
			// GameFrame doit fournir toutes les fonctions n�cessaires pour actualiser la fen�tre graphique
			// IHumanPlayerEngine fournit toutes les fonctions qui fait avancer le jeu d'un point de vue machine
			// LodeRunner d�crit le d�roulement du jeu avec des event, passage d'un panel � l'autre
			
			// Par exemple GameKeyListener est une classe controller : elle a besoin de l'engine, ainsi que du panel courant
			// Il fait avancer le jeu � chaque event (engine), et actualise la vue (panel).
			
			// L� on va juste s'occuper de HumanPlayerGamePanel (un seul panel donc) pcq c'est la priorit� du projet avec les spec
			
			
			
			
			// Pour plus tard :
			// Un menu, qui dirige soit vers l'�dition d'un environnement, soit vers la s�lection d'une partie
			// Sur le panel d'�dition de game, il peut soit revenir au menu, soit lancer la partie (ou aller sur s�lection de partie avec la game venant d'�tre �dit� pr�s�lectionn�e)
			// Sur la s�lection de la partie, on peut s�lectionner une partie �dit�e, puis lancer la game (ou revenir au menu)
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}