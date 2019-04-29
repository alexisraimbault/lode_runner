package model.services;

public interface ISummoner<Entity extends IEntity>
{
	boolean hasInstance();
	
	/*
	 * pre:
	 * 	hasInstance()
	 */
	Entity getInstance();
	
	/*
	 * pre:
	 * 	!hasInstance()
	 */
	void respawn(Entity instance);
	
	/*
	 * pre:
	 * 	hasInstance()
	 */
	void destroy();
}
