package model.services;

public interface IContent
{
	public int counts(EntityType type);
	public boolean contains(EntityType type);
	public boolean isEmpty();
	public int size();
	public int nbCharacters();
	public int nbItems();
	/**
	 * init : size() == 0
	 */
	
	/*
	 * add(type) := add(type, 1)
	 */
	public void add(EntityType type);
	
	/*
	 * post:
	 * 	counts(type) == counts(type)@before + occ
	 */
	public void add(EntityType type, int occ);
	
	/*
	 * remove(type) := remove(type, 1)
	 */
	public void remove(EntityType type);
	
	/*
	 * pre:
	 * 	counts(type) >= occ
	 * 
	 * post:
	 * 	count(type) == count(type)@before - occ
	 */
	public void remove(EntityType type, int occ);
	
	/*
	 * post:
	 * 	size() == 0
	 */
	public void clear();
	
	/*
	 * invariants:
	 * 	forall type : EntityType.values()
	 * 		counts(type) >= 0
	 * 	forall type : EntityType.values()
	 * 		contains(type) <=> counts(type) > 0
	 * 	size() == nbCharacters() + nbItems()
	 * 	size() == 0 <=> isEmpty()
	 * 	nbCharacters() >= 0
	 * 	nbItems() >= 0
	 * 	size() == sum(forall type : EntityType.values(), counts(type))
	 * 	
	 */
}
