package controller;

import model.services.IEngine;
import view.HumanPlayerGamePanel;

public class GameRunner implements Runnable
{
	
	private static final long engine_nano_time_precision = 1000000; // = 1 ms
	
	// painted every 10 times
	private static final long view_nano_time_ratio = 10; // = 10 ms
	
	private IEngine engine;
	private HumanPlayerGamePanel panel;
	private TimeConverter converter;
	
	public GameRunner(IEngine engine, HumanPlayerGamePanel panel, TimeConverter converter)
	{
		this.engine = engine;
		this.panel = panel;
		this.converter = converter;
	}
	
	@Override
	public void run()
	{
		long elapsed_nano = 0;
		int k = 0;
		
		engine.start();
		while(gameContinues(engine))
		{
			if(elapsed_nano > engine_nano_time_precision)
			{
				long elapsed = converter.getUnitsFromNanoTime(elapsed_nano);
				
				engine.step(elapsed);

				++k;
				if(k == view_nano_time_ratio)
				{
					k = 0;
					panel.repaint();
				}
				
				elapsed_nano = 0;
			}
			
			long start = System.nanoTime();
			Thread.yield();
			elapsed_nano += System.nanoTime() - start;
		}
		
		System.out.println("Status = " + engine.getStatus());
	}
	
	private boolean gameContinues(IEngine engine)
	{
		switch(engine.getStatus())
		{
		case LOSS:
			return false;
		case PAUSE:
			return false;
		case PLAYING:
			return true;
		case WIN:
			return engine.getState().getPool().getPlayer().hasOperation();
		default:
			break;
		}
		return false;
		
	}
}
