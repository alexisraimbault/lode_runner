package model.services;

public interface ITeleporter extends IStaticEntity
{
	public ITeleporter getSibling();
	public boolean hasSibling();

	public void setSibling(ITeleporter sibling);
}
