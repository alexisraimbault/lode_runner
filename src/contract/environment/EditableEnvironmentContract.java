package contract.environment;

import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.environment.EditableEnvironmentDecorator;
import model.services.EntityType;
import model.services.IDynamicEnvironment;
import model.services.IEditableEnvironment;
import model.services.Nature;

public class EditableEnvironmentContract extends EditableEnvironmentDecorator{

	public EditableEnvironmentContract(IEditableEnvironment d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * pre:
	 * 	isPlayable()
	 */
	public IDynamicEnvironment produce(){
		if(!isPlayable())
			throw new PreconditionError("in EditableEnvironment -> produce : editable env not playable");
		return delegate.produce();
	}
	
	/*
	 * pre:
	 * 
	 * post:
	 * 	@result =
	 * 		*** metal block at the bottom of the map ***
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			getCellNature(x, 0) == Nature.METAL
	 * 
	 * 		*** no holes ***
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			forall y in [0 ; getHeight() - 1]
	 * 				getCellNature(x, y) != Nature.HOLE
	 * 
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			forall y in [0 ; getHeight() - 1]
	 * 				*** only one character per block ***
	 * 				getCellContent(x, y).nbCharacters() <= 1
	 * 
	 * 				*** no characters in plenty cell ***
	 * 				getCellNature(x, y) == Nature.PLATFORM || getCellNature(x, y) == Nature.METAL =>
	 * 					getCellContent(x, y).isEmpty() !!!!! REMOVED WHEN FANTOM EXISTS
	 * 
	 * 				*** treasure on plenty cell ***
	 * 				getCellContent(x, y).contains(EntityType.TREASURE) => (y > 0) &&
	 * 					(getCellNature(x, y - 1) == Nature.PLATFORM || getCellNature(x, y - 1) == Nature.METAL)
	 * 		*** only one player on the map ***
	 * 		size of set
	 * 			forall x in [0 ; getWidth() - 1]
	 * 				forall y in [0 ; getHeight - 1]
	 * 					getCellContent(x, y).contains(EntityType.PLAYER)
	 * 			== 1
	 * 		*** at least one treasure (optional) ***
	 * 		size of set
	 * 			forall x in [0 ; getWidth() - 1]
	 * 				forall y in [0 ; getHeight - 1]
	 * 					getCellContent(x, y).contains(EntityType.TREASURE)
	 * 			> 0
	 */
	public boolean isPlayable(){
		boolean res = delegate.isPlayable();
		
		boolean metal = true;
		for(int x = 0; x < getWidth() ; x++){
			if(getCellNature(x, 0) != Nature.METAL)
				metal = false;
		}
		
		boolean holes = true;
		for(int x = 0; x < getWidth() ; x++){
			for(int y = 0; y < getHeight() ; x++){
				if(getCellNature(x, y) == Nature.HOLE)
					holes = false;
			}
		}
		
		boolean entities = true;
		for(int x = 0; x < getWidth() ; x++){
			for(int y = 0; y < getHeight() ; x++){
				if(getCellContent(x, y).nbCharacters() > 1 || ((getCellNature(x, y) == Nature.PLATFORM || getCellNature(x, y) == Nature.METAL) && !getCellContent(x, y).isEmpty()))//remove when fantoms
					entities = false;
			}
		}
		
		boolean treasure = true;
		for(int x = 0; x < getWidth() ; x++){
			for(int y = 0; y < getHeight() ; x++){
				if(getCellContent(x, y).contains(EntityType.TREASURE) && !((getCellNature(x, y - 1) == Nature.PLATFORM || getCellNature(x, y - 1) == Nature.METAL) && y>0))
					treasure = false;
			}
		}
		
		int player = 0;
		for(int x = 0; x < getWidth() ; x++){
			for(int y = 0; y < getHeight() ; x++){
				if(getCellContent(x, y).contains(EntityType.PLAYER))
					player ++;
			}
		}
		
		int nb_treasure = 0;
		for(int x = 0; x < getWidth() ; x++){
			for(int y = 0; y < getHeight() ; x++){
				if(getCellContent(x, y).contains(EntityType.TREASURE))
					nb_treasure ++;
			}
		}
		
		
		if(!((metal && holes && entities && treasure && player == 1 && nb_treasure > 0) == res))
			throw new PostconditionError("in EditableEnvironment -> isPlayable : false statement regarding the playability");
		
		return res;
	}
	
	public void checkInvariant() {
	}
}
