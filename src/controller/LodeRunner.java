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
import model.services.Status;
import view.GameFrame;
import view.HumanPlayerGamePanel;

public class LodeRunner extends JFrame
{
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
			
			IEnvironment environment = new Environment(editable.produce());
			
			final int nb_lives = 3;
			IGameState state = new GameState(environment, OperationsSpeeds.default_speeds, nb_lives);

			IHumanPlayerEngine engine = new HumanPlayerEngine(state);
			HumanPlayerGamePanel panel = new HumanPlayerGamePanel(engine);
			
			final long player_move_speed = state.getSpeeds().get(PlayerCommandType.LEFT);
			final long player_move_nano_time = 200000000; // the time reference is 200 ms for player speed
			TimeConverter converter = new TimeConverter(player_move_speed, player_move_nano_time);
			
			GameFrame frame = new GameFrame(engine, panel, converter);
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}