package contract.algorithms;

import decorator.algorithms.MoverDecorator;
import model.services.ICharacter;
import model.services.IMover;

public class MoverContract<Character extends ICharacter>  extends MoverDecorator<Character>{
	

	public MoverContract(IMover<Character> d) {
		super(d);
		checkInvariant();
	}

	public void checkInvariant() {
		
	}
	
}
