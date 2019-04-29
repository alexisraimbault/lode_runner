package model.gamestate.entities;

import model.services.EntityType;
import model.services.ICell;
import model.services.IItem;
import model.services.IPlayerSummoner;
import model.services.ISummoner;
import model.services.IPlayer;

public class PlayerSummoner extends AbstractSummoner<IPlayer> implements IPlayerSummoner
{
	private int nb_lives;
	private int treasure_score;
	private int coin_score;
	private ICell starter;
	
	public PlayerSummoner(IPlayer instance, int nb_lives)
	{
		super(instance);
		this.nb_lives = nb_lives;
		this.treasure_score = 0;
		this.coin_score = 0;
		this.starter = new Cell(instance);
	}
	
	@Override
	public int getTreasureScore()
	{
		return treasure_score;
	}

	@Override
	public int getCoinScore()
	{
		return coin_score;
	}

	@Override
	public int getNbLives()
	{
		return nb_lives;
	}

	@Override
	public void destroy()
	{
		super.destroy();
		--nb_lives;
		coin_score = 0;
	}

	@Override
	public ICell getStarter()
	{
		return starter;
	}
	
	@Override
	public <Item extends IItem> boolean canCollect(ISummoner<Item> sitem)
	{
		return sitem.getInstance().equals(getInstance());
	}

	@Override
	public <Item extends IItem> void collect(ISummoner<Item> sitem)
	{
		switch(sitem.getInstance().getType())
		{
		case COIN:
			++coin_score;
			break;
		case TREASURE:
			++treasure_score;
			break;
		default:
			assert false;
			break;
		}
		sitem.destroy();
	}
	
	@Override
	public void respawn()
	{
		getStarter().getContent().add(EntityType.PLAYER);
		super.respawn(new Player(getStarter()));
	}

	@Override
	public boolean wins(int score_to_reach)
	{
		return getInstance().getY() == getInstance().getEnvironment().getHeight() - 1 && getTreasureScore() == score_to_reach;
	}
}
