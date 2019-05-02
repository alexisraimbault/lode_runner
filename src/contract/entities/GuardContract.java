package contract.entities;

import decorator.entities.GuardDecorator;
import model.services.IGuard;

public class GuardContract extends GuardDecorator{

	public GuardContract(IGuard d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

}
