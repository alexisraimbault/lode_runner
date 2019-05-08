package model.services;

public interface IGuardMoveAccepter extends ICommandAccepter<IGuard, MoveType>
{

	/**
	 * post : 
	 * !stop_at_border.accept(type, entity) -> @result = false, break
	 * !no_plenty.accept(type, entity) -> @result = false, break
	 * hooking.accept(type, entity) -> @result = true, break
	 * walking.accept(type, entity) -> @result = true, break
	 * falling.accept(type, entity) -> @result = true, break
	 * @result = false
	 */
	
	public boolean accept(MoveType type, IGuard entity);
	/*{
		if(!stop_at_border.accept(type, entity))
			return false;
		
		if(!no_plenty.accept(type, entity))
			return false;
		
		if(hooking.accept(type, entity))
			return true;
		
		if(walking.accept(type, entity))
			return true;
		
		if(falling.accept(type, entity))
			return true;
		
		return false;
	}*/
	
	IStopAtBorderMoveAccepter<IGuard> getStop_at_border();

	IHookingMoveAccepter<IGuard> getHooking();

	INoPlentyMoveAccepter<IGuard> getNo_plenty();

	IWalkingMoveAccepter<IGuard> getWalking();

	IFallingMoveAccepter<IGuard> getFalling();
}
