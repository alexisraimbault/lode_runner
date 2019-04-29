package model.services;

public interface IPlayerSummoner extends ISummoner<IPlayer>
{
	
	int getTreasureScore();
	
	int getCoinScore();
	
	int getNbLives();
	
	ICell getStarter();
	
	/*
	 * pre:
	 * 	hasInstance()
	 * 
	 * post:
	 * 	@result = getInstance().getContent().contains(item_type)
	 */
	<Item extends IItem>
	boolean canCollect(ISummoner<Item> sitem);
	
	/*
	 * pre:
	 * 	getNbLives() > 0 (ICycleCharacter)
	 * 
	 * post:
	 * 	getNbLives() = getNbLives()@before - 1
	 * 	getCoinScore() = 0
	 */
	// void destroy()
	
	/*
	 * pre:
	 * 
	 * post:
	 * 	match item.getInstance()@before.getType()
	 * 		COIN		->	getCoinScore() = getCoinScore()@before + 1
	 * 		TREASURE	->	getTreasureScore() = getTreasureScore()@before + 1
	 */
	<Item extends IItem>
	void collect(ISummoner<Item> sitem);
	
	/*
	 * pre:
	 * 	!hasInstance()
	 * 
	 * post:
	 * 	hasInstance()
	 * 	getInstance().equals(getStarter())
	 */
	void respawn();

	/*
	 * pre:
	 * 	hasInstance()
	 * 
	 * post:
	 * 	@result = (getInstance().getY() == getInstance().getEnvironment() && getTreasureScore() == score_to_reach)
	 */
	boolean wins(int score_to_reach);
	
	/*
	 * invariants:
	 * 	getNbLives() >= 0
	 * 	getCoinScore() >= 0
	 * 	getTreasureScore() >= 0
	 */
}
