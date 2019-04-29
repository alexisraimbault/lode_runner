package model.gamestate;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import model.gamestate.entities.Cell;
import model.gamestate.entities.GuardSummoner;
import model.gamestate.entities.PlayerSummoner;
import model.gamestate.entities.Guard;
import model.gamestate.entities.Player;
import model.gamestate.entities.Treasure;
import model.gamestate.entities.TreasureSummoner;
import model.services.EntityType;
import model.services.ICell;
import model.services.ICoinSummoner;
import model.services.IContent;
import model.services.IGuardSummoner;
import model.services.IPlayerSummoner;
import model.services.ISummonerPool;
import model.services.IEnvironment;
import model.services.IGuard;
import model.services.IPlayer;
import model.services.ITreasure;
import model.services.ITreasureSummoner;

public class SummonerPool implements ISummonerPool
{
	private IPlayerSummoner splayer;
	private List<IGuardSummoner> sguards;
	private List<ITreasureSummoner> streasures;
	private List<ICoinSummoner> scoins;
	
	public SummonerPool(IEnvironment environment, int nb_lives)
	{
		this.splayer = null;
		this.sguards = new ArrayList<>();
		this.streasures = new ArrayList<>();
		this.scoins = new ArrayList<>();
		
		for(int x = 0; x < environment.getWidth(); ++x)
		{
			for(int y = 0; y < environment.getHeight(); ++y)
			{
				ICell cell = new Cell(environment, x, y);
				IContent content = environment.getCellContent(x, y);
				for(EntityType type : EntityType.values())
				{
					for(int k = 0; k < content.counts(type); ++k)
					{
						switch(type)
						{
						case PLAYER:
							splayer = new PlayerSummoner(new Player(cell), nb_lives);
							break;
						case GUARD:
							sguards.add(new GuardSummoner(new Guard(cell)));
							break;
						case TREASURE:
							streasures.add(new TreasureSummoner(new Treasure(cell)));
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	
	@Override
	public IPlayerSummoner getPlayerSummoner()
	{
		return splayer;
	}

	@Override
	public List<IGuardSummoner> getGuardSummoners()
	{
		return sguards;
	}

	@Override
	public List<ITreasureSummoner> getTreasureSummoners()
	{
		return streasures;
	}
	
	@Override
	public List<ICoinSummoner> getCoinSummoners()
	{
		return scoins;
	}

	@Override
	public IPlayer getPlayer()
	{
		return splayer.getInstance();
	}

	@Override
	public List<IGuard> getGuards()
	{
		List<IGuard> guards = new ArrayList<>();
		for(IGuardSummoner sguard : sguards)
			if(sguard.hasInstance())
				guards.add(sguard.getInstance());
		return guards;
	}

	@Override
	public List<ITreasure> getTreasures()
	{
		List<ITreasure> treasures = new ArrayList<>();
		for(ITreasureSummoner streasure : streasures)
			if(streasure.hasInstance())
				treasures.add(streasure.getInstance());
		return treasures;
	}
	
	@Override
	public void clearItems()
	{
		ListIterator<ITreasureSummoner> pstreasure = streasures.listIterator();
		while(pstreasure.hasNext())
		{
			ITreasureSummoner streasure = pstreasure.next();
			if(!streasure.hasInstance())
				pstreasure.remove();
		}
		
		ListIterator<ICoinSummoner> pscoin = scoins.listIterator();
		while(pstreasure.hasNext())
		{
			ICoinSummoner scoin = pscoin.next();
			if(!scoin.hasInstance())
				pscoin.remove();
		}
	}

}
