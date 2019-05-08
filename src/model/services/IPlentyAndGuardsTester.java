package model.services;

import java.util.function.Predicate;

public interface IPlentyAndGuardsTester extends Predicate<ICell>
{
	/**
	 * pre : cell.hasContent()
	 * post : @result = cell.getNature() == Nature.PLATFORM || cell.getNature() == Nature.METAL || cell.getContent().contains(EntityType.GUARD);
	 * 
	 * 
	 */
	public boolean test(ICell cell);
	/*{
		Nature nature = cell.getNature();
		IContent content = cell.getContent();
		return nature == Nature.PLATFORM || nature == Nature.METAL || content.contains(EntityType.GUARD);
	}*/
}
