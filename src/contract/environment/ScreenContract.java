package contract.environment;

import contract.contracterr.InvariantError;
import contract.contracterr.PreconditionError;
import decorator.environment.ScreenDecorator;
import model.services.IScreen;
import model.services.Nature;

public class ScreenContract extends ScreenDecorator{

	public ScreenContract(IScreen d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public int getWidth(){
		return super.getWidth();
	}
	public int getHeight(){
		return super.getHeight();
	}
	
	/*
	 * pre:
	 * 	0 <= x <= getWidth() - 1
	 * 	0 <= y <= getHeight() - 1
	 */
	public Nature getCellNature(int x, int y){
		if(!(0 <= x && x <= getWidth() - 1))
			throw new PreconditionError("in ScreenContract -> getCellNature : width not in the expected range");
		if(!(0 <= y && y <= getHeight() - 1))
			throw new PreconditionError("in ScreenContract -> getCellNature : height not in the expected range !");
		return super.getCellNature(x, y);
	}
	
	/*
	 * pre:
	 * 	getCellNature(x, y) == Nature.PLATFORM
	 */
	public void dig(int x, int y){
		if(!(getCellNature(x, y) == Nature.PLATFORM))
			throw new PreconditionError("in ScreenContract -> dig : not a PLATFORM !");
		super.dig(x, y);
	}
	
	/*
	 * pre:
	 * 	getCellNature(x, y) == Nature.HOLE
	 */
	public void fill(int x, int y){
		if(!(getCellNature(x, y) == Nature.HOLE))
			throw new PreconditionError("in ScreenContract -> fill : not a hole !");
		super.fill(x, y);
	}
	
	/*
	 * invariants:
	 * getWidth() >= 0
	 * getHeight >= 0
	 * getWidth() const?
	 * getHeight() const?
	 */
	
	public void checkInvariant() {
		if(!(getWidth() >= 0))
			throw new InvariantError("in ScreenContract -> invariantErr : width should be positive or null");
		if(!(getHeight() >= 0))
			throw new InvariantError("in ScreenContract -> invariantErr : height should be positive or null");
	}


}
