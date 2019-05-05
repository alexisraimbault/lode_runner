package contract.gameState;

import contract.contracterr.InvariantError;
import contract.contracterr.PreconditionError;
import decorator.gameState.EngineDecorator;
import model.services.IEngine;
import model.services.Status;

public class EngineContract extends EngineDecorator{

	public EngineContract(IEngine d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * pre:
	 * 	elapsed >= 0
	 */
	public void step(long elapsed){
		if(!(elapsed >= 0))
			throw new PreconditionError("EngineContract -> step : cant step with a negative value");
		super.step(elapsed);
	}
	
	/*
	 * pre:
	 * 	getStatus() == Status.PAUSE
	 */
	public void start(){
		if(!(	getStatus() == Status.PAUSE))
			throw new PreconditionError("EngineContract -> start : cant start if not paused");
		super.start();
	}
	
	/*
	 * pre:
	 * 	getStatus() == Status.PLAYING
	 */
	public void stop(){
		if(!(	getStatus() == Status.PLAYING))
			throw new PreconditionError("EngineContract -> stop : cant stop if not playing");
		super.stop();
	}
	
	public void checkInvariant() {
		
	}

}