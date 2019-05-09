package model.services;


public interface IPlayerDigAccepter extends ICommandAccepter<IPlayer, DigType>
{
	/**
	 * player.getY() == 0 -> @result == false
	 * 
	 * player.getNature() == Nature.EMPTY || player.getNature() == Nature.HOLE && (!(Cell.getNext(player, MoveType.DOWN).getNature().isPlenty() || Cell.getNext(player, MoveType.DOWN).getNature() == Nature.LADDER || Cell.getNext(player, MoveType.DOWN).getContent().nbCharacters() > 0)) ->  @result == false
	 * 
	 * match(type):
	 *  DIGLEFT ->  (player.getX() == 0 ||
	 *  			Cell.getNext(player, MoveType.LEFT).isPlenty() ||
	 *  			Cell.getNext(Cell.getNext(player, MoveType.LEFT), MoveType.DOWN).getNature() != Nature.PLATFORM) -> @result = false
	 *  			else -> result = true
	 *  DIGRIGHT->  (player.getX() == 0 ||
	 *  			Cell.getNext(player, MoveType.RIGHT).isPlenty() ||
	 *  			Cell.getNext(Cell.getNext(player, MoveType.RIGHT), MoveType.DOWN).getNature() != Nature.PLATFORM) -> @result = false
	 *  			else -> result = true
	 *  			
	 * 
	 */
	boolean accept(DigType type, IPlayer player);
	
}
