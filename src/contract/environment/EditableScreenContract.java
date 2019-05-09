package contract.environment;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.environment.EditableScreenDecorator;
import model.services.IDynamicScreen;
import model.services.IEditableScreen;
import model.services.Nature;

public class EditableScreenContract extends EditableScreenDecorator{

	public EditableScreenContract(IEditableScreen d) {
		super(d);
		checkInvariant();
	}
	
	/*
	 * pre:
	 * 	isPlayable()
	 * 
	 * post:
	 * 	false
	 */
	public IDynamicScreen produce(){
		if(!isPlayable())
			throw new PreconditionError("in EditableScreen -> produce : editable env not playable");
		checkInvariant();
		IDynamicScreen res = super.produce();
		checkInvariant();
		return res;
	}
	
	/*
	 * pre:
	 * 
	 * post:
	 * 	@result =
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			getCellNature(x, 0) == Nature.METAL
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			forall y in [0 ; getHeight() - 1]
	 * 				getCellNature(x, y) != Nature.HOLE
	 */
	public boolean isPlayable(){
		checkInvariant();
		boolean res = super.isPlayable();
		checkInvariant();
		boolean metal = true;
		for(int x = 0; x < getWidth() ; x++){
			if(getCellNature(x, 0) != Nature.METAL)
				metal = false;
		}
		
		boolean holes = true;
		for(int x = 0; x < getWidth() ; x++){
			for(int y = 0; y < getHeight() ; y++){
				if(getCellNature(x, y) == Nature.HOLE)
					holes = false;
			}
		}
		
		if(!((metal && holes ) == res))
			throw new PostconditionError("in EditableScreen -> isPlayable : false statement regarding the playability");
		
		return res;
	}
	
	public void checkInvariant() {
	}


}
