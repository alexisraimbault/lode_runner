package model.services;

public interface IEditableEnvironment extends IDynamicEnvironment
{
	/*
	 * pre:
	 * 	isPlayable()
	 * 
	 * post:
	 * 	false
	 */
	public IDynamicEnvironment produce();
	
	/*
	 * pre:
	 * 
	 * post:
	 * 	@result =
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			getCellNature(x, 0) == Nature.METAL
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			forall y in [0 ; getHeight - 1]
	 * 				getCellNature(x, y) != Nature.HOLE
	 * 		forall x in [0 ; getWidth() - 1]
	 * 			forall y in [0 ; getHeight - 1]
	 * 				getCellContent(x, y).nbCharacters() <= 1
	 * 				getCellNature(x, y).isPlenty() => getCellContent(x, y).isEmpty()
	 * 				(y > 0) =>
	 * 					getCellNature(x, y - 1).isPlenty() =>
	 * 						forall entity in getCellContent(x, y)
	 * 							(entity instanceof ITreasure) => 
	 */
	public boolean isPlayable();
}
