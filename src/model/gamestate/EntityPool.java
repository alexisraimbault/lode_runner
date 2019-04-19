package model.gamestate;

import java.util.ArrayList;
import java.util.List;

import model.gamestate.entities.Guard;
import model.gamestate.entities.Player;
import model.gamestate.entities.Treasure;
import model.services.EntityType;
import model.services.IContent;
import model.services.IEntityPool;
import model.services.IEnvironment;
import model.services.IGuard;
import model.services.IPlayer;
import model.services.ITreasure;

public class EntityPool implements IEntityPool
{
	private IPlayer player;
	private List<IGuard> guards;
	private List<ITreasure> treasures;
	
	public EntityPool(IEnvironment environment)
	{
		this.player = null;
		this.guards = new ArrayList<>();
		this.treasures = new ArrayList<>();
		
		for(int x = 0; x < environment.getWidth(); ++x)
		{
			for(int y = 0; y < environment.getHeight(); ++y)
			{
				IContent content = environment.getCellContent(x, y);
				for(EntityType type : EntityType.values())
				{
					for(int k = 0; k < content.counts(type); ++k)
					{
						switch(type)
						{
						case PLAYER:
							player = new Player(environment, x, y);
							break;
						case GUARD:
							guards.add(new Guard(environment, x, y));
							break;
						case TREASURE:
							treasures.add(new Treasure(environment, x, y));
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
	public IPlayer getPlayer()
	{
		return player;
	}

	@Override
	public List<IGuard> getGuards()
	{
		return guards;
	}

	@Override
	public List<ITreasure> getTreasures()
	{
		return treasures;
	}

}
