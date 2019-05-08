package model.services;

public interface IPlayerMoveAccepter extends ICommandAccepter<IPlayer, MoveType>
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
	
	public boolean accept(MoveType type, IPlayer entity);
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

	IStopAtBorderMoveAccepter<IPlayer> getStop_at_border();

	IHookingMoveAccepter<IPlayer> getHooking();

	INoPlentyMoveAccepter<IPlayer> getNo_plenty();

	IWalkingMoveAccepter<IPlayer> getWalking();

	IFallingMoveAccepter<IPlayer> getFalling();
}
