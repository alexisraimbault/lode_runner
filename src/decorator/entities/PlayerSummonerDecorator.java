package decorator.entities;

import model.services.ICell;
import model.services.IItem;
import model.services.IPlayer;
import model.services.IPlayerSummoner;
import model.services.ISummoner;

public class PlayerSummonerDecorator extends AbstractSummonerDecorator<IPlayer> implements IPlayerSummoner
{

	public PlayerSummonerDecorator(IPlayerSummoner d) {
		super(d);
	}

	@Override
	public int getTreasureScore() {
		return ((IPlayerSummoner) delegate).getTreasureScore();
	}

	@Override
	public int getCoinScore() {
		return ((IPlayerSummoner) delegate).getCoinScore();
	}

	@Override
	public int getNbLives() {
		return ((IPlayerSummoner) delegate).getNbLives() ;
	}

	@Override
	public ICell getStarter() {
		return ((IPlayerSummoner) delegate).getStarter() ;
	}

	@Override
	public <Item extends IItem> boolean canCollect(ISummoner<Item> sitem) {
		return ((IPlayerSummoner) delegate).canCollect( sitem);
	}

	@Override
	public <Item extends IItem> void collect(ISummoner<Item> sitem) {
		((IPlayerSummoner) delegate).collect( sitem);
		
	}

	@Override
	public void respawn() {
		((IPlayerSummoner) delegate). respawn();
		
	}

	@Override
	public boolean wins(int score_to_reach) {
		return ((IPlayerSummoner) delegate).wins(score_to_reach);
	}
}
