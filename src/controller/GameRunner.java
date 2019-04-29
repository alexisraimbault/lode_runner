package controller;

import java.util.List;

import model.services.ICharacter;
import model.services.IEntityPool;
import model.services.IGameState;
import model.services.IGuard;
import model.services.IHumanPlayerEngine;
import model.services.Status;
import view.HumanPlayerGamePanel;

public class GameRunner implements Runnable
{
	private IHumanPlayerEngine engine;
	private HumanPlayerGamePanel panel;
	private TimeConverter converter;
	
	public GameRunner(IHumanPlayerEngine engine, TimeConverter converter)
	{
		this.engine = engine;
		this.converter = converter;
	}
	
	public void setPanel( HumanPlayerGamePanel panel){
		this.panel = panel;
	}
	
	
	@Override
	public void run()
	{
		long elapsed_nano = 0;
		while(engine.getStatus() == Status.PAUSE)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(engine.getStatus() == Status.PLAYING)
		{
			if(elapsed_nano > 1000000)
			{
				long elapsed = converter.getUnitsFromNanoTime(elapsed_nano);
				
				engine.step(elapsed);
				
				panel.repaint();
				
				elapsed_nano = 0;
			}
			
			long start = System.nanoTime();
			Thread.yield();
			elapsed_nano += System.nanoTime() - start;
		}
	}
}
