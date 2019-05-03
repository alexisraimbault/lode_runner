package model.services;

public interface IOperationsSpeeds
{
	public long get(PlayerCommandType type);
	public long get(GuardCommandType type);
	public long getHoleSpeed();
	
	/*
	 * invariants:
	 * 	forall type in PlayerCommandType.values()
	 * 		get(type) >= 0
	 * 		get(type) const
	 * 	forall type in GuardCommandType.values()
	 * 		get(type) >= 0
	 * 		get(type) const
	 * 	getHoleSpeed() >= 0
	 * 	getHoleSpeed() const
	 */
}
