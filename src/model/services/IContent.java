package model.services;

public interface IContent
{
	public int counts(EntityType type);
	public boolean contains(EntityType type);
	
	public int size();
	public int nbCharacters();
	public int nbItems();
	
	public boolean add(EntityType type);
	public int add(EntityType type, int occ);
	public boolean remove(EntityType type);
	public int remove(EntityType type, int occ);
	public void clear();
}
