package contract.environment;

import contract.contracterr.InvariantError;
import contract.contracterr.PreconditionError;
import decorator.environment.EnvironmentDecorator;
import model.services.IContent;
import model.services.IEnvironment;

public class EnvironmentContract extends EnvironmentDecorator{

	public EnvironmentContract(IEnvironment d) {
		super(d);
	}
	
	public IContent getCellContent(int x, int y){
		if(!(0 <= x  && x <= getWidth() - 1 && 0 <= y && y<= getHeight() - 1))
			throw new PreconditionError("in Environment -> getCellContent : coordinates not in the range of expected coordinates");
		checkInvariant();
		IContent res = super.getCellContent(x,y);
		checkInvariant();
		return res;
	}
	
	public void checkInvariant() {
	}

}
