package contract.entities;

import contract.contracterr.InvariantError;
import contract.contracterr.PostconditionError;
import contract.contracterr.PreconditionError;
import decorator.entities.PlayerSummonerDecorator;
import model.services.EntityType;
import model.services.IItem;
import model.services.IPlayerSummoner;
import model.services.ISummoner;

public class PlayerSummonerContract extends PlayerSummonerDecorator{

	public PlayerSummonerContract(IPlayerSummoner d) {
		super(d);
	}

	public void checkInvariant() {
		if(! (getNbLives() >= 0))
			throw new InvariantError("nbLives should be positive or null");
		if(! (getCoinScore() >= 0))
			throw new InvariantError("the coin score should be positive or null");
		if(! (getTreasureScore() >= 0))
			throw new InvariantError("the treasure score should be positive or null");
	}
	
	public <Item extends IItem> boolean canCollect(ISummoner<Item> sitem){
		if(! hasInstance())
			throw new PreconditionError("in PlayerSummoner -> canCollect : no instance");
		if(! sitem.hasInstance())
			throw new PreconditionError("in PlayerSummoner -> canCollect : no item instance");
		checkInvariant();
		boolean res = super.canCollect(sitem);
		checkInvariant();
		if(!(res == getInstance().getContent().contains(sitem.getInstance().getType())))
			throw new PostconditionError("in PlayerSummoner -> canCollect : statements canCollect() and getInstance().getContent().contains(sitem.getInstance().getType()) should be the same");
		return res;
	}
	
	/*
	 * pre:
	 * 	getNbLives() > 0 (ICycleCharacter)
	 * 
	 * post:
	 * 	getNbLives() = getNbLives()@before - 1
	 * 	getCoinScore() = 0
	 */
	// void destroy()
	
	@Override
	public <Item extends IItem> void collect(ISummoner<Item> sitem){
		if(!canCollect(sitem))
			throw new PreconditionError("in PlayerSummoner -> colect : can't collect item");
		int coin_score_pre = getCoinScore();
		int treasure_score_pre = getTreasureScore();
		EntityType item_type = sitem.getInstance().getType();
		checkInvariant();
		super.collect(sitem);
		checkInvariant();
		if(item_type.equals(EntityType.COIN) && (!(getCoinScore() == coin_score_pre + 1)))
			throw new PostconditionError("in PlayerSummoner -> colect : coin score didn't increment after coin collection");
		if(item_type.equals(EntityType.TREASURE) && (!(getTreasureScore() == treasure_score_pre + 1)))
			throw new PostconditionError("in PlayerSummoner -> colect : treasure score didn't increment after treasure collection");
	}
	
	@Override
	public void respawn(){
		if(! hasInstance())
			throw new PreconditionError("in PlayerSummoner -> respawn : no instance");
		checkInvariant();
		super.respawn();
		checkInvariant();
		if(!(getInstance().equals(getStarter())))
			throw new PostconditionError("in PlayerSummoner -> respawn : instance should be the same as starter");
	}

	@Override
	public boolean wins(int score_to_reach){
		if(! hasInstance())
			throw new PreconditionError("in PlayerSummoner -> wins : no instance");
		checkInvariant();
		boolean res = super.wins(score_to_reach);
		checkInvariant();
		if(!(getInstance().getY() == getInstance().getEnvironment().getHeight()-1 && getTreasureScore() == score_to_reach))
			throw new PostconditionError("in PlayerSummoner -> wins : player not in top of the map, or score is not high enough");
		return res;
	}
	

}
