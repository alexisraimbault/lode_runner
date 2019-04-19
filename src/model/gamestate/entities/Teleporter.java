package model.gamestate.entities;

import model.services.EntityType;
import model.services.IEnvironment;
import model.services.ITeleporter;

public class Teleporter extends StaticEntity implements ITeleporter
{
	private ITeleporter sibling;
	
	public Teleporter(IEnvironment environment, int x, int y)
	{
		this(environment, x, y, null);
	}
	
	public Teleporter(IEnvironment environment, int x, int y, ITeleporter sibling)
	{
		super(environment, x, y);
		this.sibling = sibling;
	}

	@Override
	public boolean hasSibling()
	{
		return sibling != null;
	}

	@Override
	public void setSibling(ITeleporter sibling)
	{
		this.sibling = sibling;
	}

	@Override
	public ITeleporter getSibling()
	{
		return sibling;
	}
	
	public static ITeleporter[] create(IEnvironment environment, int x, int y, int sx, int sy)
	{
		ITeleporter[] siblings = new ITeleporter[2];
		siblings[0] = new Teleporter(environment, x, y);
		siblings[1] = new Teleporter(environment, sx, sy, siblings[0]);
		siblings[0].setSibling(siblings[1]);
		return siblings;
	}

	@Override
	public EntityType getType()
	{
		return EntityType.TELEPORTER;
	}

}
