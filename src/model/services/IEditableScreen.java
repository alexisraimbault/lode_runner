package model.services;

public interface IEditableScreen extends IDynamicScreen
{
	/*
	 * pre:
	 * 	isPlayable()
	 * 
	 * post:
	 * 	false
	 */
	public IDynamicScreen produce();
	
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
	 */
	public boolean isPlayable();
}
