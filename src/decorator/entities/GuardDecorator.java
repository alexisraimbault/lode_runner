package decorator.entities;

import model.services.GuardCommandType;
import model.services.IGuard;

public class GuardDecorator extends AbstractOperatingEntityDecorator<GuardCommandType> implements IGuard
{

	public GuardDecorator(IGuard d) {
		super(d);
	}

	@Override
	public boolean isBlocked() {
		return ((IGuard) delegate).isBlocked();
	}

	@Override
	public void block(long blocking_time) {
		((IGuard) delegate).block(blocking_time);
		
	}

	@Override
	public void unblock() {
		((IGuard) delegate).unblock();
	}

}
