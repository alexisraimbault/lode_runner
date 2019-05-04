package decorator.gameState;

import model.services.IEngine;
import model.services.IGameState;
import model.services.Status;

public class EngineDecorator implements IEngine{
	protected IEngine delegate;
	public EngineDecorator(IEngine d ){
		delegate = d;
	}
	public IGameState getState() {
		return delegate.getState();
	}
	public Status getStatus() {
		return delegate.getStatus();
	}
	public void step(long elapsed) {
		delegate.step(elapsed);
	}
	public void start() {
		delegate.start();
	}
	public void stop() {
		delegate.stop();
	}

}
