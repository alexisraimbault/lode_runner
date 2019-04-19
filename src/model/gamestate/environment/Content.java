package model.gamestate.environment;

import java.util.EnumMap;

import model.services.EntityType;
import model.services.IContent;

public class Content implements IContent
{
	private EnumMap<EntityType, Integer> map;
	private int nb_characters;
	private int nb_elem;

	public Content()
	{
		this.map = new EnumMap<>(EntityType.class);
		this.nb_characters = 0;
		this.nb_elem = 0;
		for(EntityType type : EntityType.values())
			map.put(type, 0);
	}
	@Override
	public int nbCharacters()
	{
		return nb_characters;
	}

	@Override
	public int nbItems()
	{
		return nb_elem - nb_characters;
	}

	@Override
	public boolean contains(EntityType type)
	{
		return map.containsKey(type);
	}

	@Override
	public boolean add(EntityType type)
	{
		add(type, 1);
		return true;
	}

	@Override
	public int add(EntityType type, int occ)
	{
		int before = map.get(type);
		int after = before + occ;
		int diff = after - before;
		if(type.isCharacter())
			nb_characters += diff;
		nb_elem += diff;
		map.put(type, after);
		return before;
	}

	@Override
	public boolean remove(EntityType type)
	{
		int before = remove(type, 1);
		return before >= 1;
	}

	@Override
	public int remove(EntityType type, int occ)
	{
		int before = map.get(type);
		int after = before - occ;
		after = after < 0 ? 0 : after;
		int diff = before - after;
		if(type.isCharacter())
			nb_characters -= diff;
		nb_elem -= diff;
		map.put(type, after);
		return before;
	}
	@Override
	public int counts(EntityType type)
	{
		return map.get(type);
	}
	@Override
	public void clear()
	{
		for(EntityType type : EntityType.values())
			map.put(type, 0);
	}
	@Override
	public int size()
	{
		return nb_elem;
	}
	
}
