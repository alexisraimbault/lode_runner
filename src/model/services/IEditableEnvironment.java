package model.services;

public interface IEditableEnvironment extends IDynamicEnvironment
{
	/*
	 * pre:
	 * 	isPlayable()
	 */
	public IDynamicEnvironment produce();
	
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
	public boolean isPlayable();
}
