package model.services;

import java.util.List;

public interface IShortestPathCalculator<
	Character extends ICharacter,
	CommandType extends Enum<CommandType>>
{
	/**
	 * pre : 
	 * pretargetX = target.getX();
	 * pretargetY = target.getY();
	 * post : 
	 * res = getPath(entity, target, accepter)
	 * for( move i : res ){
	 * 		entity.move(i);
	 * }
	 * res.getX() == preTargetX (=target.getX()@before);
	 * res.getY() == preTargetY (=target.getY()@before);
	 */
	public List<CommandType> getPath(Character entity, ICell target, ICommandApplier<Character, CommandType> accepter);
}
